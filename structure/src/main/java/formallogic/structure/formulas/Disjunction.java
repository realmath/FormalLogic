package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

/** The logical or. */
public final class Disjunction extends BinaryConnective {

  Disjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Disjunction substitute_(Variable variable, Term term) {
    return new Disjunction(
        getLeftOperand().substitute(variable, term), getRightOperand().substitute(variable, term));
  }
}
