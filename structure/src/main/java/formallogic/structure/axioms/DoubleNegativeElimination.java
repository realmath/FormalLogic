package formallogic.structure.axioms;

import formallogic.structure.Axiom;
import formallogic.structure.Term;
import formallogic.structure.formulas.Implication;
import formallogic.structure.formulas.Negation;

public final class DoubleNegativeElimination extends Axiom {

  public static final DoubleNegativeElimination DOUBLE_NEGATIVE_ELIMINATION =
      new DoubleNegativeElimination();

  private DoubleNegativeElimination() {}

  @Override
  protected boolean justifies_(Term formulaWithNoFreeVariables) {
    if (formulaWithNoFreeVariables instanceof Implication) {
      Implication implication = (Implication) formulaWithNoFreeVariables;
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
