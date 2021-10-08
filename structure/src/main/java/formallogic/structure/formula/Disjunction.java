package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

/** The logical or. */
public final class Disjunction extends BinaryConnective {

  Disjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Disjunction substitute_(Variable variable, Term term) {
    return new Disjunction(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
