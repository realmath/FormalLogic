package formallogic.structure.meta;

import static formallogic.structure.meta.TermDomain.TERM_DOMAIN;
import static formallogic.structure.meta.TruthFormulaDomain.TRUTH_FORMULA_DOMAIN;
import static formallogic.structure.meta.VariableDomain.VARIABLE_DOMAIN;

import formallogic.structure.common.AbstractTerm;
import formallogic.structure.core.Term;
import formallogic.structure.core.Variable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Substitution extends AbstractTerm<TruthFormulaDomain> {
  private final Term baseFormula;
  private final Term metaVariable;
  private final Term replacement;
  private final Set<Variable> variables;

  public Substitution(Term baseFormula, Term metaVariable, Term replacement) {
    super(TRUTH_FORMULA_DOMAIN);

    assert baseFormula.domain().equals(TRUTH_FORMULA_DOMAIN) : "baseFormula.domain() is wrong";
    assert metaVariable.domain().equals(VARIABLE_DOMAIN) : "metaVariable is wrong";
    assert replacement.domain().equals(TERM_DOMAIN) : "replacement is wrong";

    this.baseFormula = baseFormula;
    this.metaVariable = metaVariable;
    this.replacement = replacement;
    variables =
        Stream.concat(
                Stream.concat(baseFormula.variables().stream(), metaVariable.variables().stream()),
                replacement.variables().stream())
            .collect(Collectors.toUnmodifiableSet());
  }

  @Override
  protected Term substitute_(Variable variable, Term term) {
    return new Substitution(
        baseFormula.substitute(variable, term),
        metaVariable.substitute(variable, term),
        replacement.substitute(variable, term));
  }

  @Override
  protected Set<Variable> variables_() {
    return variables;
  }
}
