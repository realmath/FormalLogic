package formallogic.structure.testing;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class ProdTerm extends Term {
  final Term t1;
  final Term t2;
  final Domain d;

  public ProdTerm(Term t1, Term t2, Domain d) {
    this.t1 = t1;
    this.t2 = t2;
    this.d = d;
  }

  @Override
  public Domain domain() {
    return d;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    return new ProdTerm(t1.substitute(variable, term), t2.substitute(variable, term), d);
  }

  @Override
  protected Set<Variable> variables_() {
    Set<Variable> retVal = new HashSet<>();
    retVal.addAll(t1.variables());
    retVal.addAll(t2.variables());
    return Set.copyOf(retVal);
  }

  @Override
  public boolean equals(Object object) {
    return object == this
        || (object instanceof ProdTerm other
            && Objects.equals(t1, other.t1)
            && Objects.equals(t2, other.t2)
            && Objects.equals(d, other.d));
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 59 + (t1 == null ? 43 : t1.hashCode());
    result = result * 59 + (t2 == null ? 43 : t2.hashCode());
    return result * 59 + (d == null ? 43 : d.hashCode());
  }

  @Override
  public String toString() {
    return "ProdTerm(t1=" + t1 + ", t2=" + t2 + ", d=" + d + ")";
  }
}
