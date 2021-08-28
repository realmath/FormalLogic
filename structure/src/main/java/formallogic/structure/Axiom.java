package formallogic.structure;

import static formallogic.structure.domains.TruthDomain.TRUTH_DOMAIN;

public abstract class Axiom {

  public final boolean justifies(Term formula) {
    assert formula.domain().equals(TRUTH_DOMAIN) : "term must be a formula";
    assert formula.variables().isEmpty() : "should have no free variables";
    return justifies_(formula);
  }

  protected abstract boolean justifies_(Term formulaWithNoFreeVariables);
}
