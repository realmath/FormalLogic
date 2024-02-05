package formallogic.structure.meta;

import static formallogic.structure.meta.TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;
import static formallogic.structure.meta.VariableDomain.VARIABLE_DOMAIN;

import formallogic.structure.core.Domain;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Substitution extends Term {
  private final Term baseTerm;
  private final Term metaVariable;
  private final Term replacement;
  private final Set<Variable> variables;

  public Substitution(Term baseTerm, Term metaVariable, Term replacement) {
    assert baseTerm.domain().equals(TRUTH_FORMULA_DOMAIN) : "baseTerm.domain() is wrong";
    assert metaVariable.domain().equals(VARIABLE_DOMAIN) : "metaVariable is wrong";
    assert replacement.domain().equals(VARIABLE_DOMAIN) : "replacement is wrong";

    this.baseTerm = baseTerm;
    this.metaVariable = metaVariable;
    this.replacement = replacement;
    variables =
        Stream.concat(
                Stream.concat(baseTerm.variables().stream(), metaVariable.variables().stream()),
                replacement.variables().stream())
            .collect(Collectors.toUnmodifiableSet());
  }

  @Override
  public Domain domain() {
    return TRUTH_FORMULA_DOMAIN;
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    return new Substitution(
        baseTerm.substitute(variable, term),
        metaVariable.substitute(variable, term),
        replacement.substitute(variable, term));
  }

  @Override
  protected Set<Variable> variables_() {
    return variables;
  }
}
