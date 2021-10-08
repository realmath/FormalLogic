package formallogic.structure.core;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.testing.ConstTerm;
import formallogic.structure.testing.FakeDomain;
import formallogic.structure.testing.ProdTerm;
import formallogic.structure.testing.UnaryTerm;
import org.junit.jupiter.api.Test;

class TermTest {
  @Test
  public void variables() {
    Domain d = new FakeDomain();
    Domain valueDomain = new FakeDomain();
    Variable v = new Variable(d);
    Term t = new UnaryTerm(v, valueDomain);

    assertThat(t.variables()).containsExactly(v);

    Variable x = new Variable(d);
    Variable y = new Variable(d);
    Term s = new ProdTerm(x, y, d);
    assertThat(s.variables()).containsExactly(x, y);

    Term u = t.substitute(v, s);
    assertThat(u.variables()).containsExactly(x, y);
  }

  @Test
  public void substitute() {
    Domain d = new FakeDomain();
    Term t = new ConstTerm(d);
    Variable v = new Variable(d);
    Term u = new Variable(d);
    assertThat(t.substitute(v, u)).isEqualTo(t);

    Domain valueDomain = new FakeDomain();
    Term t1 = new UnaryTerm(v, valueDomain);
    assertThat(t1.substitute(v, u)).isEqualTo(new UnaryTerm(u, valueDomain));
  }
}
