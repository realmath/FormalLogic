package formallogic.structure.axioms;

import formallogic.structure.core.Term;
import formallogic.structure.formula.ForAll;
import formallogic.structure.formula.Implication;
import formallogic.structure.formula.Negation;
import formallogic.structure.proof.Axiom;

public final class DoubleNegationElimination extends Axiom {

  public static final DoubleNegationElimination DOUBLE_NEGATION_ELIMINATION =
      new DoubleNegationElimination();

  private DoubleNegationElimination() {}

  @Override
  protected boolean justifies_(Term formula) {
    if (formula instanceof ForAll) {
      ForAll forAll = (ForAll) formula;
      return justifies_(forAll.baseFormula());
    }
    if (formula instanceof Implication) {
      Implication implication = (Implication) formula;
      Term ifTrue = implication.leftOperand();
      Term thenTrue = implication.rightOperand();
      if (ifTrue instanceof Negation) {
        Term tmp = ((Negation) ifTrue).operand();
        if (tmp instanceof Negation) {
          tmp = ((Negation) tmp).operand();
          return tmp.equals(thenTrue);
        }
      }
    }
    return false;
  }
}
