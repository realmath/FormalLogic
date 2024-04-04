package formallogic.structure.formula;

import formallogic.structure.common.AbstractBinaryOperator;
import formallogic.structure.common.AbstractBinaryOperatorTest;
import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.domains.TruthDomain;
import java.util.Arrays;
import java.util.List;

final class BinaryConnectiveTest extends AbstractBinaryOperatorTest {
  private final List<Class<? extends AbstractBinaryOperator<?>>> classes =
      Arrays.asList(Conjunction.class, Disjunction.class, Equality.class, Implication.class);

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
    return TruthDomain.TRUTH_DOMAIN;
  }
}
