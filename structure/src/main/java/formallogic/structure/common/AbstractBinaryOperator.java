package formallogic.structure.common;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractBinaryOperator<T extends Domain> extends AbstractTerm<T> {

  private final Term leftOperand;
  private final Term rightOperand;
  private final Set<Variable> variables;

  protected AbstractBinaryOperator(Term leftOperand, Term rightOperand, T domain) {
    super(domain);
    assert leftOperand.domain().equals(domain) : "leftOperand";
    assert rightOperand.domain().equals(domain) : "rightOperand";
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
  protected final Set<Variable> variables_() {
    return variables;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AbstractBinaryOperator)) {
      return false;
    }
    AbstractBinaryOperator that = (AbstractBinaryOperator) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(leftOperand(), that.leftOperand())
        && Objects.equals(rightOperand(), that.rightOperand());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getClass(), leftOperand(), rightOperand());
  }
}
