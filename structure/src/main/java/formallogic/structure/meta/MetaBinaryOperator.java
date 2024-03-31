package formallogic.structure.meta;

import static formallogic.structure.meta.TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;

import formallogic.structure.common.AbstractBinaryOperator;
import formallogic.structure.core.Term;

abstract class MetaBinaryOperator extends AbstractBinaryOperator<TruthFormulaDomain> {

  MetaBinaryOperator(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand, TRUTH_FORMULA_DOMAIN);
  }
}
