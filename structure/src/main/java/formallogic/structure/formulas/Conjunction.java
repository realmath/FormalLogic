package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

/** The logical and. */
public final class Conjunction extends BinaryConnective {

  Conjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Conjunction substitute_(Variable variable, Term term) {
    return new Conjunction(
        getLeftOperand().substitute(variable, term), getRightOperand().substitute(variable, term));
  }
}
