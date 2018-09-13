package org.jim.open.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Auth Server config
 *
 * @author author
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Store the tokens in somewhere
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Bean
    public InMemoryTokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * Store the clients in somewhere
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsService cds = this.clientDetailsService();

        BaseClientDetails clientDetails = new BaseClientDetails(
                "645968e2bad447d78286724a135fa868", "test", "trust",
                "client_credentials", "ROLE_CLIENT");
        clientDetails.setClientSecret("$2a$10$peNQypvwaTk9WAZ93j3RuOkbpSv0Wy23uKenR9iJIVxG/NYiP7USa");

        Map<String, ClientDetails> map = new HashMap<>(2);
        map.put(clientDetails.getClientId(), clientDetails);
        cds.setClientDetailsStore(map);

        clients.withClientDetails(cds);
    }

    @Bean
    public InMemoryClientDetailsService clientDetailsService() {
        return new InMemoryClientDetailsService();
    }

    /**
     * Form authentication
     * Password encoder
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
