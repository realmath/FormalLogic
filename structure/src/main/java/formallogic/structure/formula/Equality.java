package formallogic.structure.formula;

import formallogic.structure.common.AbstractBinaryOperator;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;

public final class Equality extends AbstractBinaryOperator<TruthDomain> {

  Equality(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand, leftOperand.domain(), TruthDomain.TRUTH_DOMAIN);
  }

  @Override
  protected Equality substitute_(Variable variable, Term term) {
    return new Equality(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
