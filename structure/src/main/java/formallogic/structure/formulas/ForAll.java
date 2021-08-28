package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;

public final class ForAll extends QuantifierFormula {

  ForAll(Variable quantifier, Term baseFormula) {
    super(quantifier, baseFormula);
  }

  @Override
  protected ForAll substitute__(Variable variable, Term term) {
    if (variables().contains(variable)) {
      return new ForAll(getQuantifier(), getBaseFormula().substitute(variable, term));
    }
    return this;
  }
}
