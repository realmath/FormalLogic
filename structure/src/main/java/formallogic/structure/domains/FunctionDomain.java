package formallogic.structure.domains;

import formallogic.structure.Domain;
import java.util.List;
import java.util.Objects;

public final class FunctionDomain extends Domain {

  private final List<Domain> freeVariableDomains;
  private final Domain valueDomain;

  public FunctionDomain(List<Domain> freeVariableDomains, Domain valueDomain) {
    this.freeVariableDomains = List.copyOf(freeVariableDomains);
    this.valueDomain = valueDomain;
  }

  public List<Domain> getFreeVariableDomains() {
    return freeVariableDomains;
  }

  public Domain getValueDomain() {
    return valueDomain;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FunctionDomain that = (FunctionDomain) o;
    return freeVariableDomains.equals(that.freeVariableDomains)
        && valueDomain.equals(that.valueDomain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(freeVariableDomains, valueDomain);
  }
}
