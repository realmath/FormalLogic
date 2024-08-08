package formallogic.structure.formula;

import formallogic.structure.common.AbstractTerm;
import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

public abstract class QuantifierFormula extends AbstractTerm<TruthDomain> {

  private final Term baseFormula;
  private final Variable quantifier;
  private final Set<Variable> variables;

  QuantifierFormula(Variable quantifier, Term baseFormula) {
    super(TruthDomain.TRUTH_DOMAIN);
    assert baseFormula.domain().equals(TruthDomain.TRUTH_DOMAIN) : "baseFormula is not a formula";
    this.quantifier = quantifier;
    this.baseFormula = baseFormula;
    variables =
        baseFormula.variables().stream()
            .filter(x -> !x.equals(quantifier))
            .collect(Collectors.toUnmodifiableSet());
  }

  public final Variable quantifier() {
    return quantifier;
  }

  public final Term baseFormula() {
    return baseFormula;
  }

  @Override
  protected final Set<Variable> variables_() {
    return variables;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QuantifierFormula)) {
      return false;
    }
    QuantifierFormula that = (QuantifierFormula) o;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(variables, that.variables)
        && Objects.equals(quantifier.domain(), that.quantifier.domain())
        && Objects.equals(baseFormula.substitute(quantifier, that.quantifier), that.baseFormula);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(baseFormula.substitute(quantifier, new DummyTerm(quantifier.domain())));
  }

  @EqualsAndHashCode(callSuper = false)
  private static final class DummyTerm extends Term {
    private final Domain d;

    public DummyTerm(Domain d) {
      this.d = d;
    }

    @Override
    public Domain domain() {
      return d;
    }

    @Override
    protected Term substitute_(Variable variable, Term term) {
      return this;
    }

    @Override
    protected Set<Variable> variables_() {
      return Set.of();
    }
  }
}
