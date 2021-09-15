package formallogic.structure.formulas;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.Domain;
import formallogic.structure.Term;
import formallogic.structure.Variable;
import formallogic.structure.domains.TruthDomain;
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
    Term left = new UnaryTerm(vLeft, TruthDomain.TRUTH_DOMAIN);
    Term right = new ProdTerm(vRight1, vRight2, TruthDomain.TRUTH_DOMAIN);
    BinaryConnective b = new FakeBinaryConnective(left, right);
    assertThat(b.leftOperand()).isEqualTo(left);
    assertThat(b.rightOperand()).isEqualTo(right);
    assertThat(b.variables()).isEqualTo(Set.of(vLeft, vRight1, vRight2));

    assertThat(b).isEqualTo(new FakeBinaryConnective(left, right));
    assertThat(b).isNotEqualTo(new FakeBinaryConnective(right, left));
    assertThat(b).isNotEqualTo(new Implication(left, right));
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
