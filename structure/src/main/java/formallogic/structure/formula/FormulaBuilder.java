package formallogic.structure.formula;

import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;

public final class FormulaBuilder {

  private Term formula;

  private FormulaBuilder(Term formula) {
    this.formula = formula;
  }

  public static FormulaBuilder newBuilder(Term formula) {
    return new FormulaBuilder(formula);
  }

  public FormulaBuilder not() {
    formula = new Negation(formula);
    return this;
  }

  public FormulaBuilder and(Term rightOperand) {
    formula = new Conjunction(formula, rightOperand);
    return this;
  }

  public FormulaBuilder or(Term rightOperand) {
    formula = new Disjunction(formula, rightOperand);
    return this;
  }

  public FormulaBuilder implies(Term thenTrue) {
    formula = new Implication(formula, thenTrue);
    return this;
  }

  public FormulaBuilder isImpliedBy(Term ifTrue) {
    formula = new Implication(ifTrue, formula);
    return this;
  }

  public FormulaBuilder forAll(Variable variable) {
    formula = new ForAll(variable, formula);
    return this;
  }

  public FormulaBuilder exists(Variable variable) {
    formula = new Exists(variable, formula);
    return this;
  }

  /** Evaluates the specified variable with a term in the same domain. */
  public FormulaBuilder evaluate(Variable variable, Term term) {
    formula = formula.substitute(variable, term);
    return this;
  }

  public Term build() {
    return formula;
  }
}
