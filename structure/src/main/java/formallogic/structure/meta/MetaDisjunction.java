package formallogic.structure.meta;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public class MetaDisjunction extends MetaBinaryOperator {
  MetaDisjunction(Term leftOperand, Term rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  protected MetaDisjunction substitute_(Variable variable, Term term) {
    return new MetaDisjunction(
        leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
  }
}
