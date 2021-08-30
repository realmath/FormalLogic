package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

public final class Equality extends BinaryConnective {

  Equality(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Equality substitute_(Variable variable, Term term) {
    return new Equality(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
