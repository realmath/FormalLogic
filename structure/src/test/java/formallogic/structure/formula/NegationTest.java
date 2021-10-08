package formallogic.structure.formula;

import static com.google.common.truth.Truth.assertThat;
import static formallogic.structure.domains.TruthDomain.TRUTH_DOMAIN;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.UnaryTerm;
import org.junit.jupiter.api.Test;

class NegationTest {

  @Test
  void substitute() {
    Domain vDomain = new FakeDomain();
    Variable v = new Variable(vDomain);
    Term term = new UnaryTerm(v, TRUTH_DOMAIN);
    Term replacement = new UnaryTerm(new Variable(new FakeDomain()), vDomain);
    assertThat(new Negation(term).substitute(v, replacement))
        .isEqualTo(new Negation(term.substitute(v, replacement)));
  }

  @Test
  void variables() {
    Domain vDomain = new FakeDomain();
    Variable v = new Variable(vDomain);
    Term term = new UnaryTerm(v, TRUTH_DOMAIN);
    assertThat(new Negation(term).variables()).containsExactly(v);
  }
}
