package formallogic.structure.formula;

import static com.google.common.truth.Truth.assertThat;
import static formallogic.structure.domains.TruthDomain.TRUTH_DOMAIN;

import com.google.common.testing.EqualsTester;
import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.ProdTerm;
import formallogic.structure.testing.UnaryTerm;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class BinaryOperatorTest {
  private final List<Class<? extends BinaryOperator>> classes =
      Arrays.asList(Conjunction.class, Disjunction.class, Equality.class, Implication.class);

  @Test
  void beanTest() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRight1Domain = new FakeDomain();
    Variable vRight1 = new Variable(vRight1Domain);
    Domain vRight2Domain = new FakeDomain();
    Variable vRight2 = new Variable(vRight2Domain);
    Term left = new UnaryTerm(vLeft, TRUTH_DOMAIN);
    Term right = new ProdTerm(vRight1, vRight2, TRUTH_DOMAIN);
    for (Class<? extends BinaryOperator> cls : classes) {
      BinaryOperator b =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left, right);
      assertThat(b.leftOperand()).isEqualTo(left);
      assertThat(b.rightOperand()).isEqualTo(right);
      assertThat(b.variables()).isEqualTo(Set.of(vLeft, vRight1, vRight2));
    }
  }

  @Test
  void substitute() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRight1Domain = new FakeDomain();
    Variable vRight1 = new Variable(vRight1Domain);
    Domain vRight2Domain = new FakeDomain();
    Variable vRight2 = new Variable(vRight2Domain);
    Term left = new UnaryTerm(vLeft, TRUTH_DOMAIN);
    Term right = new ProdTerm(vRight1, vRight2, TRUTH_DOMAIN);
    Term replacement = new UnaryTerm(left, vRight1Domain);
    for (Class<? extends BinaryOperator> cls : classes) {
      BinaryOperator b =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left, right);
      assertThat(b.substitute(vRight1, replacement))
          .isEqualTo(
              cls.getDeclaredConstructor(Term.class, Term.class)
                  .newInstance(left, right.substitute(vRight1, replacement)));
    }
  }

  @Test
  void equalsTest() throws Exception {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRightDomainA = new FakeDomain();
    Variable vRightA = new Variable(vRightDomainA);
    Domain vRightDomainB = new FakeDomain();
    Variable vRightB = new Variable(vRightDomainB);
    Term left1 = new UnaryTerm(vLeft, TRUTH_DOMAIN);
    Term right1 = new ProdTerm(vRightA, vRightB, TRUTH_DOMAIN);
    Term left2 = new UnaryTerm(vLeft, TRUTH_DOMAIN);
    Term right2 = new ProdTerm(vRightA, vRightB, TRUTH_DOMAIN);
    Term right3 = new ProdTerm(vRightB, vRightA, TRUTH_DOMAIN);
    EqualsTester equalsTester = new EqualsTester();
    for (Class<? extends BinaryOperator> cls : classes) {
      BinaryOperator b1 =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left1, right1);
      BinaryOperator b2 =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left2, right2);
      BinaryOperator b3 =
          cls.getDeclaredConstructor(Term.class, Term.class).newInstance(left1, right3);
      equalsTester.addEqualityGroup(b1, b2);
      equalsTester.addEqualityGroup(b3);
    }
    equalsTester.testEquals();
  }
}
