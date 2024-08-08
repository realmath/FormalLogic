package formallogic.structure.meta;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public class MetaImplication extends MetaBinaryOperator {
  MetaImplication(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected MetaImplication substitute_(Variable variable, Term term) {
    return new MetaImplication(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
