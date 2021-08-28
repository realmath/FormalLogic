package formallogic.structure;

import java.util.Set;

public final class Variable extends Term {

  private final Domain valueDomain;

  /** Creates a new variable in the specified {@link Domain}. */
  public Variable(Domain valueDomain) {
    this.valueDomain = valueDomain;
  }

  @Override
  protected Set<Variable> variables_() {
    return Set.of(this);
  }

  @Override
  public Domain domain() {
    return valueDomain;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    if (this.equals(variable)) {
      return term;
    }
    return this;
  }
}
