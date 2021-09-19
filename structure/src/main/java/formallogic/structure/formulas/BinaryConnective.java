package formallogic.structure.formulas;

import formallogic.structure.Term;
import formallogic.structure.Variable;
import formallogic.structure.domains.TruthDomain;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class BinaryConnective extends Formula {

  private final Term leftOperand;
  private final Term rightOperand;
  private final Set<Variable> variables;

  BinaryConnective(Term leftOperand, Term rightOperand) {
    assert leftOperand.domain().equals(TruthDomain.TRUTH_DOMAIN) : "leftOperand";
    assert rightOperand.domain().equals(TruthDomain.TRUTH_DOMAIN) : "rightOperand";
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
    if (obj == null || !(obj instanceof BinaryConnective)) {
      return false;
    }
    BinaryConnective that = (BinaryConnective) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(leftOperand(), that.leftOperand())
        && Objects.equals(rightOperand(), that.rightOperand());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getClass(), leftOperand(), rightOperand());
  }
}
