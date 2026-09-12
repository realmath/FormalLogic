package formallogic.structure.core;

import java.util.Set;

/**
 * A logical variable whose identity is the identity of this Java object.
 *
 * <p>This class intentionally does not override {@link Object#equals(Object)} or {@link
 * Object#hashCode()}. Consequently, two separately constructed variables are distinct even when
 * they belong to the same {@link Domain}. Substitution and free-variable sets use that same
 * identity: clients must reuse a {@code Variable} instance for every occurrence of one logical
 * variable.
 *
 * <p>Quantified formulas account for bound-variable renaming when testing alpha-equivalence, but
 * variable identity still determines which occurrences a quantifier binds. Code that serializes,
 * copies, or reconstructs formulas must therefore preserve the sharing of variable instances rather
 * than independently recreating each occurrence.
 */
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
