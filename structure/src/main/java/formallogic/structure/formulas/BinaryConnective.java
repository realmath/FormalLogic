package formallogic.structure.formulas;

import formallogic.structure.core.Term;
import formallogic.structure.domains.TruthDomain;

abstract class BinaryConnective extends BinaryOperator {

  BinaryConnective(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
    assert leftOperand.domain().equals(TruthDomain.TRUTH_DOMAIN)
        : "leftOperand should be in TRUTH_DOMAIN";
  }
}
