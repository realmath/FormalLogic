package formallogic.structure.testing;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Objects;
import java.util.Set;

public class ConstTerm extends Term {
  private final Domain d;

  public ConstTerm(Domain d) {
    this.d = d;
  }

  @Override
  public Domain domain() {
    return d;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    return this;
  }

  @Override
  protected Set<Variable> variables_() {
    return Set.of();
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof ConstTerm other)) {
      return false;
    }
    return other.canEqual(this) && Objects.equals(d, other.d);
  }

  protected boolean canEqual(Object object) {
    return object instanceof ConstTerm;
  }

  @Override
  public int hashCode() {
    return 59 + (d == null ? 43 : d.hashCode());
  }
}
