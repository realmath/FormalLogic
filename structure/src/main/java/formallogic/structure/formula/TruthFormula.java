package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Set;

public final class TruthFormula extends Formula {

  public static final TruthFormula TRUE = new TruthFormula();

  private TruthFormula() {}

  @Override
  protected TruthFormula substitute_(Variable variable, Term term) {
    return this;
  }

  @Override
  protected Set<Variable> variables_() {
    return Set.of();
  }
}
