package org.loit.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "file:${user.dir}/src/test/resources/db-config.properties",
})
public interface DBConfig extends Config {

  @Key("dbMainUrl")
  String DBConnectionUrl();
  @Key("dbMainUrlAlt")
  String DBConnectionUrlAlt();

  @Key("user")
  String DBUser();

  @Key("password")
  String DBPassword();

  @Key("useSSL")
  String DBUseSSL();
  @Key("requireSSL")
  String DBRequireSSL();
  @Key("clientCertificateKeyStoreUrl")
  String DBClientCertificateKeyStoreUrl();
  @Key("clientCertificateKeyStorePassword")
  String DBClientCertificateKeyStorePassword();

  @Key("trustCertificateKeyStoreUrl")
  String DBTrustCertificateKeyStoreUrl();

}
