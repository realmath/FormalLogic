package formallogic.structure.core;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.ProdTerm;
import formallogic.structure.testing.UnaryTerm;
import org.junit.jupiter.api.Test;

class TermTest {
  @Test
  public void variables_unaryTerm() {
    Domain d = new FakeDomain();
    Domain valueDomain = new FakeDomain();
    Variable v = new Variable(d);
    Term unaryTerm = new UnaryTerm(v, valueDomain);

    assertThat(unaryTerm.variables()).containsExactly(v);
  }

  @Test
  public void variables_prodTerm() {
    Domain d = new FakeDomain();
    Variable x = new Variable(d);
    Variable y = new Variable(d);
    Term prodTerm = new ProdTerm(x, y, d);
    assertThat(prodTerm.variables()).containsExactly(x, y);
  }

  @Test
  public void variables_unaryTermSubstituteProdTerm() {
    Domain d = new FakeDomain();
    Domain valueDomain = new FakeDomain();
    Variable v = new Variable(d);
    Term unaryTerm = new UnaryTerm(v, valueDomain);

    Variable x = new Variable(d);
    Variable y = new Variable(d);
    Term prodTerm = new ProdTerm(x, y, d);

    Term substitutedTerm = unaryTerm.substitute(v, prodTerm);
    assertThat(substitutedTerm.variables()).containsExactly(x, y);
  }

  @Test
  public void constTermSubstituteVariable() {
    Domain d = new FakeDomain();
    Term constTerm = new ConstTerm(d);
    Variable v = new Variable(d);
    Term u = new Variable(d);
    assertThat(constTerm.substitute(v, u)).isEqualTo(constTerm);
  }

  @Test
  public void unaryTermSubstituteVariable() {
    Domain d = new FakeDomain();
    Variable v = new Variable(d);
    Term u = new Variable(d);
    Domain valueDomain = new FakeDomain();
    Term unaryTerm = new UnaryTerm(v, valueDomain);
    assertThat(unaryTerm.substitute(v, u)).isEqualTo(new UnaryTerm(u, valueDomain));
  }
}
