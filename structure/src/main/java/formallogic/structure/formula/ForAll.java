package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public final class ForAll extends QuantifierFormula {

  ForAll(Variable quantifier, Term baseFormula) {
    super(quantifier, baseFormula);
  }

  @Override
  protected ForAll substitute_(Variable variable, Term term) {
    assert variables().contains(variable) : "variable is not free";
    if (term.variables().contains(quantifier())) {
      Variable newQuantifier = new Variable(quantifier().domain());
      ForAll captureAvoidingThis =
          new ForAll(newQuantifier, baseFormula().substitute(quantifier(), newQuantifier));
      return (ForAll) captureAvoidingThis.substitute(variable, term);
    }
    return new ForAll(quantifier(), baseFormula().substitute(variable, term));
  }
}
