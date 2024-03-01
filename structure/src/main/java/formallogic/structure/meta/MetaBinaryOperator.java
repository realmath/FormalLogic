package formallogic.structure.meta;

import static formallogic.structure.meta.TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class MetaBinaryOperator extends Term {

  private final Term leftOperand;
  private final Term rightOperand;
  private final Set<Variable> variables;

  MetaBinaryOperator(Term leftOperand, Term rightOperand) {
    assert leftOperand.domain().equals(TRUTH_FORMULA_DOMAIN) : "leftOperand domain";
    assert rightOperand.domain().equals(TRUTH_FORMULA_DOMAIN) : "rightOperand domain";
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    variables =
        Stream.concat(leftOperand.variables().stream(), rightOperand.variables().stream())
            .collect(Collectors.toUnmodifiableSet());
  }

  public final Term leftOperand() {
    return leftOperand;
  }

  public final Term rightOperand() {
    return rightOperand;
  }

  @Override
  public final Domain domain() {
    return TRUTH_FORMULA_DOMAIN;
  }

  @Override
  protected Set<Variable> variables_() {
    return variables;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MetaBinaryOperator)) {
      return false;
    }
    MetaBinaryOperator that = (MetaBinaryOperator) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(leftOperand(), that.leftOperand())
        && Objects.equals(rightOperand(), that.rightOperand());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getClass(), leftOperand(), rightOperand());
  }
}
