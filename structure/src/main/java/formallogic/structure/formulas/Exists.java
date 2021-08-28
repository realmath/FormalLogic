package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

public final class Exists extends QuantifierFormula {

  Exists(Variable quantifier, Term baseFormula) {
    super(quantifier, baseFormula);
  }

  @Override
  protected Exists substitute__(Variable variable, Term term) {
    if (variables().contains(variable)) {
      return new Exists(getQuantifier(), getBaseFormula().substitute(variable, term));
    }
    return this;
  }
}
