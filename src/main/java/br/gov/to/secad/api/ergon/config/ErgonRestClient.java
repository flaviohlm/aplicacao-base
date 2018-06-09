package br.gov.to.secad.api.ergon.config;

import br.gov.to.secad.api.ergon.enumerator.ErgonRestMethods;
import br.gov.to.secad.api.ergon.enumerator.ErgonRestResponseStatus;
import br.gov.to.secad.api.ergon.json.*;
import br.gov.to.secad.api.main.util.TokenUtil;
import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import br.gov.to.secad.main.util.MessagesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wandyer.silva
 */
@Component
@PropertySource(value = "/WEB-INF/application.yml", ignoreResourceNotFound = true)
public class ErgonRestClient {

    private static Logger logger = LogManager.getLogger();

    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder invocationBuilder;
    private Response response;

    private final TokenUtil tokenUtil;

    @Value("${rest.servicesUrl}")
    private String restServiceUrl;

    @Value("${rest.hashKey}")
    private String hashKey;

    private ObjectMapper mapper;

    private String token;

    public ErgonRestClient(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @PostConstruct
    public void init() {

        ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.READ_TIMEOUT, 60000) //30000
                .property(ClientProperties.CONNECT_TIMEOUT, 10000);

        client = ClientBuilder.newClient(clientConfig);

        mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
    }

    private boolean requestToken() {
        this.token = tokenUtil.requestToken(hashKey);
        return token != null;
    }

    //Single ===================================================================
    public DadosPessoaisResponse findDadosPessoais(ServidorRequest servidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.PESSOAIS.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(servidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                if (response.getStatus() == 200) {
                    try {
                        String jsonString = response.readEntity(String.class);
                        ObjectMapper mapper = new ObjectMapper();
                        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setDateFormat(df);

                        return mapper.readValue(
                                jsonString, DadosPessoaisResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public DadosFuncionaisResponse findDadosFuncionais(ServidorRequest servidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.FUNCIONAIS.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(servidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                if (response.getStatus() == 200) {
                    try {
                        String jsonString = response.readEntity(String.class);
                        ObjectMapper mapper = new ObjectMapper();
                        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setDateFormat(df);

                        return mapper.readValue(
                                jsonString, DadosFuncionaisResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public DadosSimplesResponse findDadosSimples(ServidorRequest servidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.SIMPLES.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(servidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                if (response.getStatus() == 200) {
                    try {
                        String jsonString = response.readEntity(String.class);
                        ObjectMapper mapper = new ObjectMapper();
                        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setDateFormat(df);

                        return mapper.readValue(
                                jsonString, DadosSimplesResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public DadosCompletosResponse findDadosCompletos(ServidorRequest servidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.COMPLETOS.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(servidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                if (response.getStatus() == 200) {
                    try {
                        String jsonString = response.readEntity(String.class);
                        ObjectMapper mapper = new ObjectMapper();
                        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setDateFormat(df);

                        return mapper.readValue(
                                jsonString, DadosCompletosResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    logger.error(SpringSecurityUtil.getCPF());
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());

                }
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    //List =====================================================================
    public List<DadosPessoaisResponse> findListaDadosPessoais(List<ServidorRequest> listaServidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.PESSOAIS_LISTA.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(listaServidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                List<DadosPessoaisResponse> listaDadosPessoais = new ArrayList<>();

                if (response.getStatus() == 200) {
                    String jsonString = response.readEntity(String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    mapper.setDateFormat(df);

                    try {
                        listaDadosPessoais = mapper.readValue(jsonString,
                                TypeFactory.defaultInstance().constructCollectionType(List.class,
                                        DadosPessoaisResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                return listaDadosPessoais;
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public List<DadosFuncionaisResponse> findListaDadosFuncionais(List<ServidorRequest> listaServidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.FUNCIONAIS_LISTA.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(listaServidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                List<DadosFuncionaisResponse> listaDadosFuncionais = new ArrayList<>();

                if (response.getStatus() == 200) {
                    String jsonString = response.readEntity(String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    mapper.setDateFormat(df);

                    try {
                        listaDadosFuncionais = mapper.readValue(jsonString,
                                TypeFactory.defaultInstance().constructCollectionType(List.class,
                                        DadosFuncionaisResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                return listaDadosFuncionais;
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public List<DadosSimplesResponse> findListaDadosSimples(List<ServidorRequest> listaServidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.SIMPLES_LISTA.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(listaServidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                List<DadosSimplesResponse> listaDadosSimples = new ArrayList<>();

                if (response.getStatus() == 200) {
                    String jsonString = response.readEntity(String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    mapper.setDateFormat(df);

                    try {
                        listaDadosSimples = mapper.readValue(jsonString,
                                TypeFactory.defaultInstance().constructCollectionType(List.class,
                                        DadosSimplesResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                return listaDadosSimples;
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public List<DadosCompletosResponse> findListaDadosCompletos(List<ServidorRequest> listaServidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.COMPLETOS_LISTA.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(listaServidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                List<DadosCompletosResponse> listaDadosCompletos = new ArrayList<>();

                if (response.getStatus() == 200) {
                    String jsonString = response.readEntity(String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    mapper.setDateFormat(df);

                    try {
                        listaDadosCompletos = mapper.readValue(jsonString,
                                TypeFactory.defaultInstance().constructCollectionType(List.class,
                                        DadosCompletosResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                return listaDadosCompletos;
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    public List<DadosVinculoResponse> listaVinculos(String cpf) {
        if (requestToken()) {

            webTarget = client
                    .target(restServiceUrl)
                    .path("ergon")
                    .path(ErgonRestMethods.LISTA_VINCULOS.getLabel())
                    .queryParam("cpf", cpf);

            invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                    .header("token", token);

            response = invocationBuilder.get();

            List<DadosVinculoResponse> listaVinculos = new ArrayList<>();

            if (response.getStatus() == 200) {

                String jsonString = response.readEntity(String.class);
                ObjectMapper mapper = new ObjectMapper();
                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                mapper.setDateFormat(df);

                try {
                    listaVinculos = mapper.readValue(jsonString,
                            TypeFactory.defaultInstance().constructCollectionType(List.class,
                                    DadosVinculoResponse.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (response.getStatus() == 401) { //Token inválido
                    MessagesUtil.addError("Erro de autenticação");
                    return null;
                }

                if (response.getStatus() == 204) { //Retornou null
                    return null;
                }

                if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                    MessagesUtil.addError("Serviço não disponível");
                    return null;
                }

                MessagesUtil.addError("Erro: " + response.getStatus());

                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            return listaVinculos;
        }
        return null;
    }

    //LISTA DE SECRETARIOS==============================================================================================
    public List<DadosSecretariosPorPastaResponse> findListaSecreatriosPorPasta() {
        if (requestToken()) {
            webTarget = client
                    .target(restServiceUrl)
                    .path("ergon")
                    .path(ErgonRestMethods.LISTA_SECRETARIOS.getLabel());

            invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                    .header("token", token);

            response = invocationBuilder.get();

            List<DadosSecretariosPorPastaResponse> listaSecretariosXml = new ArrayList<>();

            if (response.getStatus() == 200) {

                String jsonString = response.readEntity(String.class);

                try {
                    listaSecretariosXml = mapper.readValue(jsonString,
                            TypeFactory.defaultInstance().constructCollectionType(List.class,
                                    DadosSecretariosPorPastaResponse.class));
                } catch (IOException e) {
                    logger.error(e);
                }
            } else {
                if (response.getStatus() == 401) { //Token inválido
                    MessagesUtil.addError("Erro de autenticação");
                    return null;
                }

                if (response.getStatus() == 204) { //Retornou null
                    return null;
                }

                if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                    MessagesUtil.addError("Serviço não disponível");
                    return null;
                }

                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            return listaSecretariosXml;
        }
        return null;
    }

    //Aux ======================================================================
    public Boolean verificarServidor(ServidorRequest servidorRequest) {
        if (requestToken()) {
            try {
                webTarget = client
                        .target(restServiceUrl)
                        .path("ergon")
                        .path(ErgonRestMethods.VERIFICAR_SERVIDOR.getLabel());

                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
                        .header("token", token);

                String json = mapper.writeValueAsString(servidorRequest);

                response = invocationBuilder.post(Entity.json(json));

                if (response.getStatus() == 200) {
                    response.bufferEntity();
                    return response.readEntity(String.class).equalsIgnoreCase(ErgonRestResponseStatus.SUCESSO.getLabel());
                } else {

                    if (response.getStatus() == 204) { //Inexistente
                        return false;
                    }

                    if (response.getStatus() == 401) { //Token inválido
                        MessagesUtil.addError("Erro de autenticação");
                        return null;
                    }

                    if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
                        MessagesUtil.addError("Serviço não disponível");
                        return null;
                    }

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
            } catch (JsonProcessingException ex) {
                logger.error(ex);
            }
        }
        return null;
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}