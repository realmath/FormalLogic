package formallogic.structure.formula;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import org.junit.jupiter.api.Test;

final class TruthFormulaTest {
  @Test
  public void substitute() {
    Domain d = new FakeDomain();
    Variable v = new Variable(d);
    Term t = new ConstTerm(d);

    assertThat(TruthFormula.TRUE.substitute(v, t)).isEqualTo(TruthFormula.TRUE);
  }

  @Test
  public void variables() {
    assertThat(TruthFormula.TRUE.variables()).isEmpty();
  }
}
