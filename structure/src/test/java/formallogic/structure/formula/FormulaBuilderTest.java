package formallogic.structure.formula;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.domains.TruthDomain;
import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.UnaryTerm;
import org.junit.jupiter.api.Test;

final class FormulaBuilderTest {
  @Test
  public void unaryBuilderTest() {
    Term f = new UnaryTerm(new ConstTerm(new FakeDomain()), TruthDomain.TRUTH_DOMAIN);

    assertThat(FormulaBuilder.newBuilder(f).not().build()).isEqualTo(new Negation(f));
  }

  @Test
  public void binaryBuilderTest() {
    Term f = new UnaryTerm(new ConstTerm(new FakeDomain()), TruthDomain.TRUTH_DOMAIN);
    Term g = new UnaryTerm(new ConstTerm(new FakeDomain()), TruthDomain.TRUTH_DOMAIN);

    assertThat(FormulaBuilder.newBuilder(f).not().build()).isEqualTo(new Negation(f));
    assertThat(FormulaBuilder.newBuilder(f).and(g).build()).isEqualTo(new Conjunction(f, g));
    assertThat(FormulaBuilder.newBuilder(f).or(g).build()).isEqualTo(new Disjunction(f, g));
    assertThat(FormulaBuilder.newBuilder(f).implies(g).build()).isEqualTo(new Implication(f, g));
    assertThat(FormulaBuilder.newBuilder(f).isImpliedBy(g).build())
        .isEqualTo(new Implication(g, f));
    assertThat(FormulaBuilder.newBuilder(f).equalsTo(g).build()).isEqualTo(new Equality(f, g));
  }

  @Test
  public void quantifierBuilderTest() {
    Domain d = new FakeDomain();
    Variable quantifier = new Variable(d);
    Term t = new UnaryTerm(quantifier, TruthDomain.TRUTH_DOMAIN);

    assertThat(FormulaBuilder.newBuilder(t).forAll(quantifier).build())
        .isEqualTo(new ForAll(quantifier, t));
    assertThat(FormulaBuilder.newBuilder(t).exists(quantifier).build())
        .isEqualTo(new Exists(quantifier, t));
  }

  @Test
  public void evaluateBuilderTest() {
    Domain d = new FakeDomain();
    Variable v = new Variable(d);
    Term t = new UnaryTerm(v, TruthDomain.TRUTH_DOMAIN);
    Term u = new UnaryTerm(new Variable(new FakeDomain()), d);

    assertThat(FormulaBuilder.newBuilder(t).evaluate(v, u).build()).isEqualTo(t.substitute(v, u));
  }
}
