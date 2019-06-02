package de.aldist.smarthomeappserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2RemoteResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Value("${client.id}")
  private String clientId;
  @Value("${client.secret}")
  private String clientSecret;

  @Value("${remote-auth.url}")
  private String remoteAuthServerUrl;

  @Primary
  @Bean
  public RemoteTokenServices tokenServices() {
    final RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl(this.remoteAuthServerUrl);
    tokenService.setClientId(this.clientId);
    tokenService.setClientSecret(this.clientSecret);

    return tokenService;
  }
}
