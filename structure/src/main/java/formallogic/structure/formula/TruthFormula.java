package formallogic.structure.formula;

import formallogic.structure.common.AbstractTerm;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Set;

public final class TruthFormula extends AbstractTerm<TruthDomain> {

  public static final TruthFormula TRUE = new TruthFormula();

  private TruthFormula() {
    super(TruthDomain.TRUTH_DOMAIN);
  }

  @Override
  protected TruthFormula substitute_(Variable variable, Term term) {
    return this;
  }

  @Override
  protected Set<Variable> variables_() {
    return Set.of();
  }
}
