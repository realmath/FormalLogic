package formallogic.structure.axioms;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import formallogic.structure.formula.FormulaBuilder;
import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.UnaryTerm;
import org.junit.jupiter.api.Test;

class DoubleNegationEliminationTest {
  @Test
  public void justify() {
    Term t = new ConstTerm(TruthDomain.TRUTH_DOMAIN);
    Term s = FormulaBuilder.newBuilder(t).not().not().build();

    Term p = FormulaBuilder.newBuilder(s).implies(t).build();

    assertThat(DoubleNegationElimination.DOUBLE_NEGATION_ELIMINATION.justifies(p)).isTrue();
  }

  @Test
  public void justify_forAll() {
    Variable x = new Variable(new FakeDomain());
    Term t = new UnaryTerm(x, TruthDomain.TRUTH_DOMAIN);
    Term s = FormulaBuilder.newBuilder(t).not().not().build();

    Term p = FormulaBuilder.newBuilder(s).implies(t).forAll(x).build();

    assertThat(DoubleNegationElimination.DOUBLE_NEGATION_ELIMINATION.justifies(p)).isTrue();
  }
}
