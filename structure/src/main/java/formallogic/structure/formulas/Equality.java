package formallogic.structure.formulas;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

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
