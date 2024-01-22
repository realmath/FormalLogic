package formallogic.structure.domains;

import formallogic.structure.core.Domain;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@AllArgsConstructor
@Getter
public final class FunctionDomain extends Domain {
  private final Domain variableDomain;
  private final Domain valueDomain;
}
