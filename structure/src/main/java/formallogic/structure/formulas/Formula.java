package formallogic.structure.formulas;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.domains.TruthDomain;

abstract class Formula extends Term {
  @Override
  public final Domain domain() {
    return TruthDomain.TRUTH_DOMAIN;
  }
}
