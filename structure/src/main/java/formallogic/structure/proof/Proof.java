package formallogic.structure.proof;

import formallogic.structure.core.Term;
import formallogic.structure.formula.FormulaBuilder;
import java.util.List;
import java.util.Objects;

public final class Proof {

  private final Axiom axiom;
  private final List<Proof> premises;
  private final Term conclusion;

  private Proof(Axiom axiom, List<Proof> premises, Term conclusion) {
    this.axiom = axiom;
    this.premises = premises;
    assert premises == List.copyOf(premises) : "mutable";
    this.conclusion = conclusion;
  }

  public Axiom axiom() {
    return axiom;
  }

  public List<Proof> premises() {
    return premises;
  }

  public Term conclusion() {
    return conclusion;
  }

  /** The axiom verifies the conclusion. */
  public static Proof newProof(Axiom axiom, Term conclusion) {
    assert axiom.justifies(conclusion) : "axiom does not justify conclusion";
    return new Proof(axiom, List.of(), conclusion);
  }

  /** The axiom verifies that the premise implies the conclusion. */
  public static Proof newProof(Axiom axiom, Proof premise, Term conclusion) {
    assert axiom.justifies(
            FormulaBuilder.newBuilder(premise.conclusion()).implies(conclusion).build())
        : "premise does not imply conclusion";
    return new Proof(axiom, List.of(premise), conclusion);
  }

  /** The axiom verifies that the two premises imply the conclusion. */
  public static Proof newProof(Axiom axiom, Proof premise1, Proof premise2, Term conclusion) {
    assert axiom.justifies(
            FormulaBuilder.newBuilder(premise1.conclusion())
                .and(premise2.conclusion())
                .implies(conclusion)
                .build())
        : "premises does not imply conclusion";
    return new Proof(axiom, List.of(premise1, premise2), conclusion);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof Proof other)) {
      return false;
    }
    return Objects.equals(axiom, other.axiom)
        && Objects.equals(premises, other.premises)
        && Objects.equals(conclusion, other.conclusion);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 59 + (axiom == null ? 43 : axiom.hashCode());
    result = result * 59 + (premises == null ? 43 : premises.hashCode());
    return result * 59 + (conclusion == null ? 43 : conclusion.hashCode());
  }
}
