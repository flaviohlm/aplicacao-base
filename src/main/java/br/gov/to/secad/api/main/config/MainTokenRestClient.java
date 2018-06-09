package br.gov.to.secad.api.main.config;

import br.gov.to.secad.api.main.enumerator.MainRestMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author wandyer.silva
 */
@Component
@PropertySource(value = "/WEB-INF/application.yml", ignoreResourceNotFound = true)
public class MainTokenRestClient {

    private Client client;

    @Value("${rest.servicesUrl}")
    private String restServiceUrl;

    private static Logger logger = LogManager.getLogger();

    public MainTokenRestClient() {
    }

    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.READ_TIMEOUT, 30000)
                .property(ClientProperties.CONNECT_TIMEOUT, 5000);

        client = ClientBuilder.newClient(clientConfig);
    }

    public String validateToken(String token) {
        try {
            WebTarget webTarget = client
                    .target(restServiceUrl)
                    .path(MainRestMethods.VALIDATE_TOKEN.getLabel())
                    .queryParam("token", token);

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            Response response = invocationBuilder.get();

            if (response.getStatus() == 200) {
                response.bufferEntity();
                return response.readEntity(String.class);
            } else {

                if (response.getStatus() == 404) { // API não disponível
                    logger.info("Error 404 - API não disponível");
                    return null;
                }

                logger.info("Erro " + response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

    }

    public String requestToken(String hashKey) {
        try {
            WebTarget webTarget = client
                    .target(restServiceUrl)
                    .path(MainRestMethods.TOKEN.getLabel());

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                    .header("hashkey", hashKey);

            Response response = invocationBuilder.get();

            if (response.getStatus() == 200) {
                response.bufferEntity();
                return response.readEntity(String.class);
            } else {

                if (response.getStatus() == 404) { // API não disponível
                    logger.info("Error 404 - API não disponível");
                    return null;
                }

                logger.info("Erro " + response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
