package formallogic.structure;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;

public abstract class Term {

  public abstract Domain getValueDomain();

  /**
   * Evaluates the term by substituting the given variable with the given term. The given variable
   * and the given term must have the same value domain.
   */
  public final Term substitute(Variable variable, Term term) {
    assert variable.getValueDomain().equals(term.getValueDomain())
        : "variable and term of different type";
    Term result = substitute_(variable, term);
    assert getValueDomain().equals(result.getValueDomain())
        : "evaluation is of a different type than original";
    assert ((BooleanSupplier)
                () -> {
                  Set<Variable> expectedFreeVariables = new HashSet<>(getFreeVariables());
                  boolean containsVariable = expectedFreeVariables.remove(variable);
                  if (containsVariable) {
                    expectedFreeVariables.addAll(term.getFreeVariables());
                  }
                  return result.getFreeVariables().equals(expectedFreeVariables);
                })
            .getAsBoolean()
        : "result does not have the expected set of free variables";
    return result;
  }

  protected abstract Term substitute_(Variable variable, Term term);

  /** Returns free variables of the term. */
  public final Set<Variable> getFreeVariables() {
    Set<Variable> retVal = getFreeVariables_();
    assert Set.copyOf(retVal) == retVal : "mutable";
    return retVal;
  }

  protected abstract Set<Variable> getFreeVariables_();
}
