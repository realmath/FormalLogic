package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

/** Logical implication. */
public final class Implication extends BinaryConnective {

  Implication(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Implication substitute_(Variable variable, Term term) {
    return new Implication(
        getLeftOperand().substitute(variable, term), getRightOperand().substitute(variable, term));
  }
}
