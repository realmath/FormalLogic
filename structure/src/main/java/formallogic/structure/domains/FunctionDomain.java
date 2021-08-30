package formallogic.structure.domains;

import formallogic.structure.Domain;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public final class FunctionDomain extends Domain {

  private final List<Domain> variableDomains;
  private final Domain valueDomain;

  public FunctionDomain(List<Domain> variableDomains, Domain valueDomain) {
    this.variableDomains = List.copyOf(variableDomains);
    this.valueDomain = valueDomain;
  }
}
