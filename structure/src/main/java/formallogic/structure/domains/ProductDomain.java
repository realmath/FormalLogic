package formallogic.structure.domains;

import formallogic.structure.core.Domain;
import java.util.Objects;

public final class ProductDomain extends Domain {
  private final Domain leftDomain;
  private final Domain rightDomain;

  public ProductDomain(Domain leftDomain, Domain rightDomain) {
    this.leftDomain = leftDomain;
    this.rightDomain = rightDomain;
  }

  public Domain leftDomain() {
    return leftDomain;
  }

  public Domain rightDomain() {
    return rightDomain;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof ProductDomain other)) {
      return false;
    }
    return Objects.equals(leftDomain, other.leftDomain)
        && Objects.equals(rightDomain, other.rightDomain);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 59 + (leftDomain == null ? 43 : leftDomain.hashCode());
    return result * 59 + (rightDomain == null ? 43 : rightDomain.hashCode());
  }
}
