package formallogic.structure;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

final class VariableTest {

  @Test
  void getFreeVariables() {
    Domain d = new Domain() {};
    Variable v = new Variable(d);
    assertThat(v.getFreeVariables()).containsExactly(v);
  }

  @Test
  void getValueDomain() {
    Domain d = new Domain() {};
    Variable v = new Variable(d);
    assertThat(v.getValueDomain()).isEqualTo(d);
  }

  @Test
  void substitute() {
    Domain d = new Domain() {};
    Variable v = new Variable(d);
    Term t = new Variable(d);
    assertThat(v.substitute(v, t)).isEqualTo(t);
  }

  @Test
  void substitute_unrelatedVariable() {
    Domain d = new Domain() {};
    Variable v = new Variable(d);
    Variable u = new Variable(d);
    Term t = new Variable(d);
    assertThat(v.substitute(u, t)).isEqualTo(v);
  }
}
