package formallogic.structure.testing;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
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
}
