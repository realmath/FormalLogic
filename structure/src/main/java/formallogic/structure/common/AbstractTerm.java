package formallogic.structure.common;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;

public abstract class AbstractTerm<T extends Domain> extends Term {
  private final T domain;

  protected AbstractTerm(T domain) {
    this.domain = domain;
  }

  @Override
  public final Domain domain() {
    return domain;
  }
}
