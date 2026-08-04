package formallogic.structure.formula;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.UnaryTerm;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class EqualityTest {

  @Test
  public void acceptsOperandsInSameObjectDomainAndReturnsTruth() {
    Domain domain = new FakeDomain();
    Term left = new ConstTerm(domain);
    Term right = new ConstTerm(domain);

    Equality equality = new Equality(left, right);

    assertThat(equality.leftOperand()).isEqualTo(left);
    assertThat(equality.rightOperand()).isEqualTo(right);
    assertThat(equality.domain()).isEqualTo(TruthDomain.TRUTH_DOMAIN);
  }

  @Test
  public void rejectsOperandsInDifferentDomains() {
    Term left = new ConstTerm(new FakeDomain());
    Term right = new ConstTerm(new FakeDomain());

    assertThrows(AssertionError.class, () -> new Equality(left, right));
  }

  @Test
  public void aggregatesVariablesAndSubstitutesOperands() {
    Domain operandDomain = new FakeDomain();
    Domain variableDomain = new FakeDomain();
    Variable variable = new Variable(variableDomain);
    Term left = new UnaryTerm(variable, operandDomain);
    Term right = new ConstTerm(operandDomain);
    Term replacement = new ConstTerm(variableDomain);
    Equality equality = new Equality(left, right);

    assertThat(equality.variables()).isEqualTo(Set.of(variable));
    assertThat(equality.substitute(variable, replacement))
        .isEqualTo(new Equality(left.substitute(variable, replacement), right));
  }
}
