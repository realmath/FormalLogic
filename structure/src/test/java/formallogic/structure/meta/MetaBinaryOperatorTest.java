package formallogic.structure.meta;

import formallogic.structure.common.AbstractBinaryOperator;
import formallogic.structure.common.AbstractBinaryOperatorTest;
import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import java.util.Arrays;
import java.util.List;

final class MetaBinaryOperatorTest extends AbstractBinaryOperatorTest {
  private final List<Class<? extends AbstractBinaryOperator<?>>> classes =
      Arrays.asList(MetaConjunction.class, MetaDisjunction.class);

  @Override
  protected List<Class<? extends AbstractBinaryOperator<?>>> classes() {
    return classes;
  }

  @Override
  protected AbstractBinaryOperator<?> newInstance(
      Class<? extends AbstractBinaryOperator<?>> cls, Term left, Term right) throws Exception {
    return cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left, right);
  }

  @Override
  protected Domain baseDomain() {
    return TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;
  }
}
