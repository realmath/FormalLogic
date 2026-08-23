package formallogic.structure.domains;

import formallogic.structure.core.Domain;
import java.util.Objects;

public final class FunctionDomain extends Domain {
  private final Domain variableDomain;
  private final Domain valueDomain;

  public FunctionDomain(Domain variableDomain, Domain valueDomain) {
    this.variableDomain = variableDomain;
    this.valueDomain = valueDomain;
  }

  public Domain variableDomain() {
    return variableDomain;
  }

  public Domain valueDomain() {
    return valueDomain;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof FunctionDomain other)) {
      return false;
    }
    return Objects.equals(variableDomain, other.variableDomain)
        && Objects.equals(valueDomain, other.valueDomain);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 59 + (variableDomain == null ? 43 : variableDomain.hashCode());
    return result * 59 + (valueDomain == null ? 43 : valueDomain.hashCode());
  }
}
