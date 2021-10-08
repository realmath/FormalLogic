package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class BinaryOperator extends Formula {

  private final Term leftOperand;
  private final Term rightOperand;
  private final Set<Variable> variables;

  BinaryOperator(Term leftOperand, Term rightOperand) {
    assert leftOperand.domain().equals(rightOperand.domain())
        : "leftOperand and rightOperand should be in the same domain";
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
  protected Set<Variable> variables_() {
    return variables;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BinaryOperator)) {
      return false;
    }
    BinaryOperator that = (BinaryOperator) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(leftOperand(), that.leftOperand())
        && Objects.equals(rightOperand(), that.rightOperand());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getClass(), leftOperand(), rightOperand());
  }
}
