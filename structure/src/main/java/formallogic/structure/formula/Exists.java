package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public final class Exists extends QuantifierFormula {

  Exists(Variable quantifier, Term baseFormula) {
    super(quantifier, baseFormula);
  }

  @Override
  protected Exists substitute_(Variable variable, Term term) {
    return new Exists(getQuantifier(), getBaseFormula().substitute(variable, term));
  }
}
