package formallogic.structure.testing;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Objects;
import java.util.Set;

public final class UnaryTerm extends Term {
  private final Term t;
  private final Domain d;

  public UnaryTerm(Term t, Domain d) {
    this.t = t;
    this.d = d;
  }

  @Override
  public Domain domain() {
    return d;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    return new UnaryTerm(t.substitute(variable, term), d);
  }

  @Override
  protected Set<Variable> variables_() {
    return t.variables();
  }

  @Override
  public boolean equals(Object object) {
    return object == this
        || (object instanceof UnaryTerm other
            && Objects.equals(t, other.t)
            && Objects.equals(d, other.d));
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 59 + (t == null ? 43 : t.hashCode());
    return result * 59 + (d == null ? 43 : d.hashCode());
  }

  @Override
  public String toString() {
    return "UnaryTerm(t=" + t + ", d=" + d + ")";
  }
}
