package formallogic.structure.core;

import java.util.Set;

public final class Variable extends Term {

  private final Domain domain;

  /** Creates a new variable in the specified {@link Domain}. */
  public Variable(Domain domain) {
    this.domain = domain;
  }

  @Override
  protected Set<Variable> variables_() {
    return Set.of(this);
  }

  @Override
  public Domain domain() {
    return domain;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    if (this.equals(variable)) {
      return term;
    }
    return this;
  }
}
