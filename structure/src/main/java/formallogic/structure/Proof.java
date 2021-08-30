package formallogic.structure;

import formallogic.structure.formulas.FormulaBuilder;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public final class Proof {

  private final Axiom axiom;
  private final Set<Proof> premises;
  private final Term conclusion;

  private Proof(Axiom axiom, Set<Proof> premises, Term conclusion) {
    this.axiom = axiom;
    this.premises = premises;
    assert premises == Set.copyOf(premises) : "mutable";
    this.conclusion = conclusion;
  }

  /** The axiom verifies the conclusion. */
  public static Proof newProof(Axiom axiom, Term conclusion) {
    assert axiom.justifies(conclusion) : "axiom does not justify conclusion";
    return new Proof(axiom, Set.of(), conclusion);
  }

  /** The axiom verifies that the premise implies the conclusion. */
  public static Proof newProof(Axiom axiom, Proof premise, Term conclusion) {
    assert axiom.justifies(
            FormulaBuilder.newBuilder(premise.getConclusion()).implies(conclusion).build())
        : "premise does not imply conclusion";
    return new Proof(axiom, Set.of(premise), conclusion);
  }

  /** The axiom verifies that the two premises imply the conclusion. */
  public static Proof newProof(Axiom axiom, Proof premise1, Proof premise2, Term conclusion) {
    assert axiom.justifies(
            FormulaBuilder.newBuilder(premise1.getConclusion())
                .and(premise2.getConclusion())
                .implies(conclusion)
                .build())
        : "premises does not imply conclusion";
    return new Proof(axiom, Set.of(premise1, premise2), conclusion);
  }
}
