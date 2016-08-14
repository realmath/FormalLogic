package formallogic.structure.formulas;

import formallogic.structure.Domain;
import formallogic.structure.Term;
import formallogic.structure.domains.TruthDomain;

abstract class Formula extends Term {
  @Override
  public final Domain getValueDomain() {
    return TruthDomain.TRUTH_DOMAIN;
  }
}
