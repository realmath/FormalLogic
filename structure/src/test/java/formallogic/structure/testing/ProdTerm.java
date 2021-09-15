package formallogic.structure.testing;

import formallogic.structure.Domain;
import formallogic.structure.Term;
import formallogic.structure.Variable;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
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
}
