package formallogic.structure.formula;

import formallogic.structure.common.AbstractTerm;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Objects;
import java.util.Set;

/** Negation of a Formula. */
public final class Negation extends AbstractTerm<TruthDomain> {

  private final Term operand;

  Negation(Term operand) {
    super(TruthDomain.TRUTH_DOMAIN);
    assert operand.domain().equals(TruthDomain.TRUTH_DOMAIN) : "operand";
    this.operand = operand;
  }

  public Term operand() {
    return operand;
  }

  @Override
  protected Negation substitute_(Variable variable, Term term) {
    return new Negation(operand.substitute(variable, term));
  }

  @Override
  protected Set<Variable> variables_() {
    return operand.variables();
  }

  @Override
  public boolean equals(Object object) {
    return object == this
        || (object instanceof Negation other && Objects.equals(operand, other.operand));
  }

  @Override
  public int hashCode() {
    return 59 + (operand == null ? 43 : operand.hashCode());
  }
}
