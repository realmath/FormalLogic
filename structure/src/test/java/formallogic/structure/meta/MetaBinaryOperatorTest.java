package formallogic.structure.meta;

import static com.google.common.truth.Truth.assertThat;
import static formallogic.structure.meta.TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;
import static org.mockito.Mockito.mock;

import formallogic.structure.core.Term;
import formallogic.structure.testing.ConstTerm;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MetaBinaryOperatorTest {
  private final List<Class<? extends MetaBinaryOperator>> classes = Arrays.asList();

  @Test
  public void domain() {
    MetaBinaryOperator metaBinaryOperator = mock(MetaBinaryOperator.class);

    assertThat(metaBinaryOperator.domain()).isEqualTo(TRUTH_FORMULA_DOMAIN);
  }

  @Test
  public void beanTest() throws Exception {
    Term left = new ConstTerm(TRUTH_FORMULA_DOMAIN);
    Term right = new ConstTerm(TRUTH_FORMULA_DOMAIN);

    for (Class<? extends MetaBinaryOperator> cls : classes) {
      MetaBinaryOperator b =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left, right);
      assertThat(b.leftOperand()).isEqualTo(left);
      assertThat(b.rightOperand()).isEqualTo(right);
    }
  }
}
