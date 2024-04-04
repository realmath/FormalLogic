package formallogic.structure.common;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.testing.EqualsTester;
import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.ProdTerm;
import formallogic.structure.testing.UnaryTerm;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public abstract class AbstractBinaryOperatorTest {

  protected abstract List<Class<? extends AbstractBinaryOperator<?>>> classes();

  protected abstract AbstractBinaryOperator<?> newInstance(
      Class<? extends AbstractBinaryOperator<?>> cls, Term left, Term right) throws Exception;

  protected abstract Domain baseDomain();

  @Test
  public void beanTest() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRight1Domain = new FakeDomain();
    Variable vRight1 = new Variable(vRight1Domain);
    Domain vRight2Domain = new FakeDomain();
    Variable vRight2 = new Variable(vRight2Domain);
    Term left = new UnaryTerm(vLeft, baseDomain());
    Term right = new ProdTerm(vRight1, vRight2, baseDomain());
    for (Class<? extends AbstractBinaryOperator<?>> cls : classes()) {
      AbstractBinaryOperator<?> b = newInstance(cls, left, right);
      assertThat(b.leftOperand()).isEqualTo(left);
      assertThat(b.rightOperand()).isEqualTo(right);
      assertThat(b.variables()).isEqualTo(Set.of(vLeft, vRight1, vRight2));
    }
  }

  @Test
  public void substitute() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRight1Domain = new FakeDomain();
    Variable vRight1 = new Variable(vRight1Domain);
    Domain vRight2Domain = new FakeDomain();
    Variable vRight2 = new Variable(vRight2Domain);
    Term left = new UnaryTerm(vLeft, baseDomain());
    Term right = new ProdTerm(vRight1, vRight2, baseDomain());
    Term replacement = new UnaryTerm(left, vRight1Domain);
    for (Class<? extends AbstractBinaryOperator<?>> cls : classes()) {
      AbstractBinaryOperator<?> b = newInstance(cls, left, right);
      assertThat(b.substitute(vRight1, replacement))
          .isEqualTo(newInstance(cls, left, right.substitute(vRight1, replacement)));
    }
  }

  @Test
  public void equalsTest() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRightDomainA = new FakeDomain();
    Variable vRightA = new Variable(vRightDomainA);
    Domain vRightDomainB = new FakeDomain();
    Variable vRightB = new Variable(vRightDomainB);
    Term left1 = new UnaryTerm(vLeft, baseDomain());
    Term right1 = new ProdTerm(vRightA, vRightB, baseDomain());
    Term left2 = new UnaryTerm(vLeft, baseDomain());
    Term right2 = new ProdTerm(vRightA, vRightB, baseDomain());
    Term right3 = new ProdTerm(vRightB, vRightA, baseDomain());
    EqualsTester equalsTester = new EqualsTester();
    for (Class<? extends AbstractBinaryOperator<?>> cls : classes()) {
      AbstractBinaryOperator<?> b1 = newInstance(cls, left1, right1);
      AbstractBinaryOperator<?> b2 = newInstance(cls, left2, right2);
      AbstractBinaryOperator<?> b3 = newInstance(cls, left1, right3);
      equalsTester.addEqualityGroup(b1, b2);
      equalsTester.addEqualityGroup(b3);
    }
    equalsTester.testEquals();
  }
}
