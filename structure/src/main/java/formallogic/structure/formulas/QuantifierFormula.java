package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

abstract class QuantifierFormula extends Formula {

  private final Term baseFormula;
  private final Variable quantifier;
  private final Set<Variable> freeVariables;

  QuantifierFormula(Variable quantifier, Term baseFormula) {
    assert baseFormula.getValueDomain().equals(TruthDomain.TRUTH_DOMAIN)
        : "baseFormula is not a formula";
    this.quantifier = quantifier;
    this.baseFormula = baseFormula;
    freeVariables =
        baseFormula
            .getFreeVariables()
            .stream()
            .filter(x -> x.equals(quantifier))
            .collect(Collectors.toUnmodifiableSet());
  }

  public final Variable getQuantifier() {
    return quantifier;
  }

  public final Term getBaseFormula() {
    return baseFormula;
  }

  @Override
  protected Set<Variable> getFreeVariables_() {
    return freeVariables;
  }

  @Override
  protected final Term substitute_(Variable variable, Term term) {
    assert variable.getValueDomain().equals(term.getValueDomain());
    return substitute__(variable, term);
  }

  protected abstract Term substitute__(Variable variable, Term term);

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || (o instanceof QuantifierFormula)) {
      return false;
    }
    QuantifierFormula that = (QuantifierFormula) o;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(getBaseFormula(), that.getBaseFormula())
        && Objects.equals(getQuantifier(), that.getQuantifier());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getBaseFormula(), getQuantifier());
  }
}
