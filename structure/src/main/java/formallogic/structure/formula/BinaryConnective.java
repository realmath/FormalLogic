package formallogic.structure.formula;

import formallogic.structure.common.AbstractBinaryOperator;
import formallogic.structure.core.Term;
import formallogic.structure.domains.TruthDomain;

abstract class BinaryConnective extends AbstractBinaryOperator<TruthDomain> {

  BinaryConnective(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand, TruthDomain.TRUTH_DOMAIN);
  }
}
