package formallogic.structure.core;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;

public abstract class Term {

  /** The value domain of the term. */
  public abstract Domain domain();

  /**
   * Evaluates the term by substituting the given variable with the given term. The given variable
   * and the given term must have the same value domain.
   */
  public final Term substitute(Variable variable, Term term) {
    assert variable.domain().equals(term.domain()) : "variable and term of different type";
    Term result = substitute_(variable, term);
    assert domain().equals(result.domain()) : "evaluation is of a different type than original";
    assert ((BooleanSupplier)
                () -> {
                  Set<Variable> expectedVariables = new HashSet<>(variables());
                  boolean containsVariable = expectedVariables.remove(variable);
                  if (containsVariable) {
                    expectedVariables.addAll(term.variables());
                  }
                  return result.variables().equals(expectedVariables);
                })
            .getAsBoolean()
        : "result does not have the expected set of variables";
    return result;
  }

  /** Returns free variables of the term. */
  public final Set<Variable> variables() {
    Set<Variable> retVal = variables_();
    assert Set.copyOf(retVal) == retVal : "mutable";
    return retVal;
  }

  protected abstract Term substitute_(Variable variable, Term term);

  protected abstract Set<Variable> variables_();
}
