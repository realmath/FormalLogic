package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public final class FormulaBuilder {

  private final Term formula;

  private FormulaBuilder(Term formula) {
    this.formula = formula;
  }

  public static FormulaBuilder newBuilder(Term formula) {
    return new FormulaBuilder(formula);
  }

  public FormulaBuilder not() {
    return new FormulaBuilder(new Negation(formula));
  }

  public FormulaBuilder and(Term rightOperand) {
    return new FormulaBuilder(new Conjunction(formula, rightOperand));
  }

  public FormulaBuilder or(Term rightOperand) {
    return new FormulaBuilder(new Disjunction(formula, rightOperand));
  }

  public FormulaBuilder implies(Term thenTrue) {
    return new FormulaBuilder(new Implication(formula, thenTrue));
  }

  public FormulaBuilder isImpliedBy(Term ifTrue) {
    return new FormulaBuilder(new Implication(ifTrue, formula));
  }

  public FormulaBuilder forAll(Variable variable) {
    return new FormulaBuilder(new ForAll(variable, formula));
  }

  public FormulaBuilder exists(Variable variable) {
    return new FormulaBuilder(new Exists(variable, formula));
  }

  /** Evaluates the specified variable with a term in the same domain. */
  public FormulaBuilder evaluate(Variable variable, Term term) {
    return new FormulaBuilder(formula.substitute(variable, term));
  }

  public Term build() {
    return formula;
  }
}
