package formallogic.structure.meta;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public class MetaConjunction extends MetaBinaryOperator {
  MetaConjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected MetaConjunction substitute_(Variable variable, Term term) {
    return new MetaConjunction(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
