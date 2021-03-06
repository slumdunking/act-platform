package no.mnemonic.act.platform.auth.properties.module;

import no.mnemonic.act.platform.auth.properties.PropertiesBasedAccessController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class PropertiesBasedAccessControllerProvider implements Provider<PropertiesBasedAccessController> {

  @Inject
  @Named("access.controller.properties.file")
  private String propertiesFile;
  @Inject
  @Named("access.controller.read.interval")
  private String readInterval;

  // There should only be one instance of this access controller. Ever.
  // This is because the same instance will be bound to multiple interfaces (AccessController, OrganizationResolver, SubjectResolver).
  // This is also the reason why this provider is marked with @Singleton.
  private PropertiesBasedAccessController accessController;

  @Override
  public PropertiesBasedAccessController get() {
    if (accessController == null) {
      accessController = PropertiesBasedAccessController.builder()
              .setPropertiesFile(propertiesFile)
              .setReadingInterval(Long.parseLong(readInterval))
              .build();
    }

    return accessController;
  }

}
