package formallogic.structure.domains;

import formallogic.structure.Domain;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@Getter
public final class FunctionDomain extends Domain {

  private final List<Domain> variableDomains;
  private final Domain valueDomain;

  public FunctionDomain(List<Domain> variableDomains, Domain valueDomain) {
    this.variableDomains = List.copyOf(variableDomains);
    this.valueDomain = valueDomain;
  }
}
