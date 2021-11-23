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

class QuantifierFormulaTest {
  private final List<Class<? extends QuantifierFormula>> classes =
      Arrays.asList(ForAll.class, Exists.class);

  @Test
  void beanTest() throws Exception {
    Domain d1 = new FakeDomain();
    Domain d2 = new FakeDomain();
    Domain d3 = new FakeDomain();
    Variable v1 = new Variable(d1);
    Variable v2 = new Variable(d2);
    Variable v3 = new Variable(d3);
    Term t = new ProdTerm(v1, v2, TRUTH_DOMAIN);
    for (Class<? extends QuantifierFormula> cls : classes) {
      QuantifierFormula q =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v1, t);
      assertThat(q.quantifier()).isEqualTo(v1);
      assertThat(q.baseFormula()).isEqualTo(t);
      assertThat(q.variables()).isEqualTo(Set.of(v2));

      q = cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v2, t);
      assertThat(q.quantifier()).isEqualTo(v2);
      assertThat(q.baseFormula()).isEqualTo(t);
      assertThat(q.variables()).isEqualTo(Set.of(v1));

      q = cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v3, t);
      assertThat(q.quantifier()).isEqualTo(v3);
      assertThat(q.baseFormula()).isEqualTo(t);
      assertThat(q.variables()).isEqualTo(Set.of(v1, v2));
    }
  }

  @Test
  void substitute() throws Exception {
    Domain d1 = new FakeDomain();
    Domain d2 = new FakeDomain();
    Domain d3 = new FakeDomain();
    Variable v1 = new Variable(d1);
    Variable v2 = new Variable(d2);
    Variable v3 = new Variable(d3);
    Term t = new ProdTerm(v1, v2, TRUTH_DOMAIN);
    Term replacement = new UnaryTerm(v3, d2);
    for (Class<? extends QuantifierFormula> cls : classes) {
      QuantifierFormula q =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v1, t);
      assertThat(q.substitute(v2, replacement))
          .isEqualTo(
              cls.getDeclaredConstructor(Variable.class, Term.class)
                  .newInstance(v1, t.substitute(v2, replacement)));
    }
  }

  @Test
  void equalsTest() throws Exception {
    Domain d = new FakeDomain();
    Domain e = new FakeDomain();
    Variable v_d_1 = new Variable(d);
    Variable v_e = new Variable(e);
    Variable v_d_2 = new Variable(d);
    Term v_d_1__v_e__1 = new ProdTerm(v_d_1, v_e, TRUTH_DOMAIN);
    Term v_d_1__v_e__2 = new ProdTerm(v_d_1, v_e, TRUTH_DOMAIN);
    Term v_d_2__v_3 = new ProdTerm(v_d_2, v_e, TRUTH_DOMAIN);
    EqualsTester equalsTester = new EqualsTester();
    for (Class<? extends QuantifierFormula> cls : classes) {
      QuantifierFormula q1 =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v_d_1, v_d_1__v_e__1);
      QuantifierFormula q2 =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v_d_1, v_d_1__v_e__2);
      QuantifierFormula q3 =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v_e, v_d_1__v_e__1);
      QuantifierFormula q4 =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v_d_2, v_d_1__v_e__1);
      QuantifierFormula q5 =
          cls.getDeclaredConstructor(Variable.class, Term.class).newInstance(v_d_2, v_d_2__v_3);
      equalsTester.addEqualityGroup(q1, q2, q5);
      equalsTester.addEqualityGroup(q3);
      equalsTester.addEqualityGroup(q4);
    }
    equalsTester.testEquals();
  }
}
