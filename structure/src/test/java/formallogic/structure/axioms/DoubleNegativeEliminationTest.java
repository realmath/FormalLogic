package formallogic.structure.axioms;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import formallogic.structure.core.Term;
import formallogic.structure.domains.TruthDomain;
import formallogic.structure.formula.FormulaBuilder;
import formallogic.structure.testing.ConstTerm;
import org.junit.jupiter.api.Test;

class DoubleNegativeEliminationTest {
  @Test
  public void justify() {
    Term t = new ConstTerm(TruthDomain.TRUTH_DOMAIN);
    Term s = FormulaBuilder.newBuilder(t).not().not().build();

    Term p = FormulaBuilder.newBuilder(s).implies(t).build();

    assertThat(DoubleNegativeElimination.DOUBLE_NEGATIVE_ELIMINATION.justifies(p)).isTrue();
  }
}
