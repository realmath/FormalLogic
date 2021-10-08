package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

/** The logical and. */
public final class Conjunction extends BinaryConnective {

  Conjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected Conjunction substitute_(Variable variable, Term term) {
    return new Conjunction(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
