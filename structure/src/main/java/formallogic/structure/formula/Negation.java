package formallogic.structure.formula;

import formallogic.structure.common.AbstractTerm;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/** Negation of a Formula. */
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@Getter
public final class Negation extends AbstractTerm<TruthDomain> {

  private final Term operand;

  Negation(Term operand) {
    super(TruthDomain.TRUTH_DOMAIN);
    assert operand.domain().equals(TruthDomain.TRUTH_DOMAIN) : "operand";
    this.operand = operand;
  }

  @Override
  protected Negation substitute_(Variable variable, Term term) {
    return new Negation(operand.substitute(variable, term));
  }

  @Override
  protected Set<Variable> variables_() {
    return operand.variables();
  }
}
