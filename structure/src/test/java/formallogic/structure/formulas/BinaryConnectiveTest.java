package formallogic.structure.formulas;

import static com.google.common.truth.Truth.assertThat;
import static formallogic.structure.domains.TruthDomain.TRUTH_DOMAIN;

import com.google.common.testing.EqualsTester;
import formallogic.structure.Domain;
import formallogic.structure.Term;
import formallogic.structure.Variable;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.ProdTerm;
import formallogic.structure.testing.UnaryTerm;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class BinaryConnectiveTest {
  @Test
  void beanTest() {
    Domain vLeftDomain = new FakeDomain();
    Variable vLeft = new Variable(vLeftDomain);
    Domain vRight1Domain = new FakeDomain();
    Variable vRight1 = new Variable(vRight1Domain);
    Domain vRight2Domain = new FakeDomain();
    Variable vRight2 = new Variable(vRight2Domain);
    Term left = new UnaryTerm(vLeft, TRUTH_DOMAIN);
    Term right = new ProdTerm(vRight1, vRight2, TRUTH_DOMAIN);
    BinaryConnective b = new FakeBinaryConnective(left, right);
    assertThat(b.leftOperand()).isEqualTo(left);
    assertThat(b.rightOperand()).isEqualTo(right);
    assertThat(b.variables()).isEqualTo(Set.of(vLeft, vRight1, vRight2));
  }

  @Test
  void equalsTest() {
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
    new EqualsTester()
        .addEqualityGroup(new Implication(left1, right1), new Implication(left2, right2))
        .addEqualityGroup(new Implication(left1, right3))
        .addEqualityGroup(new Conjunction(left1, right1), new Conjunction(left2, right2))
        .addEqualityGroup(new Conjunction(left1, right3))
        .addEqualityGroup(new Disjunction(left1, right1), new Disjunction(left2, right2))
        .addEqualityGroup(new Disjunction(left1, right3))
        .addEqualityGroup(new Equality(left1, right1), new Equality(left2, right2))
        .addEqualityGroup(new Equality(left1, right3))
        .testEquals();
  }

  static final class FakeBinaryConnective extends BinaryConnective {

    FakeBinaryConnective(Term leftOperand, Term rightOperand) {
      super(leftOperand, rightOperand);
    }

    @Override
    protected Term substitute_(Variable variable, Term term) {
      return new FakeBinaryConnective(
          leftOperand().substitute(variable, term), rightOperand().substitute(variable, term));
    }
  }
}
