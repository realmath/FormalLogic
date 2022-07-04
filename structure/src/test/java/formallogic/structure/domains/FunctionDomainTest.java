package formallogic.structure.domains;

import static com.google.common.truth.Truth.assertThat;

import formallogic.structure.core.Domain;
import formallogic.structure.testing.FakeDomain;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

final class FunctionDomainTest {
  @Test
  public void variableDomainsImmutable() {
    List<Domain> domains = new ArrayList<>();
    domains.add(new FakeDomain());
    FunctionDomain fd = new FunctionDomain(domains, new FakeDomain());

    assertThat(List.copyOf(fd.variableDomains())).isSameInstanceAs(fd.variableDomains());
  }
}
