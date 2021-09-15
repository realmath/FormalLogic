package formallogic.structure.testing;

import formallogic.structure.Domain;
import formallogic.structure.Term;
import formallogic.structure.Variable;
import java.util.Set;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
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
}
