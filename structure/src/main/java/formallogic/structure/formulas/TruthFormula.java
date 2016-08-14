package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;
import java.util.Set;

public final class TruthFormula extends Formula {

  public static final TruthFormula TRUE = new TruthFormula();

  private TruthFormula() {}

  @Override
  protected TruthFormula substitute_(Variable variable, Term term) {
    return this;
  }

  @Override
  protected Set<Variable> getFreeVariables_() {
    return Set.of();
  }
}
