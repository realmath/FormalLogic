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
  private final Set<Variable> freeVariables;

  BinaryConnective(Term leftOperand, Term rightOperand) {
    assert leftOperand.getValueDomain().equals(TruthDomain.TRUTH_DOMAIN) : "leftOperand";
    assert rightOperand.getValueDomain().equals(TruthDomain.TRUTH_DOMAIN) : "rightOperand";
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    freeVariables =
        Stream.concat(
                leftOperand.getFreeVariables().stream(), rightOperand.getFreeVariables().stream())
            .collect(Collectors.toUnmodifiableSet());
  }

  public final Term getLeftOperand() {
    return leftOperand;
  }

  public final Term getRightOperand() {
    return rightOperand;
  }

  @Override
  protected Set<Variable> getFreeVariables_() {
    return freeVariables;
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
        && Objects.equals(getLeftOperand(), that.getLeftOperand())
        && Objects.equals(getRightOperand(), that.getRightOperand());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getLeftOperand(), getRightOperand());
  }
}
