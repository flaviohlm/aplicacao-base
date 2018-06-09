/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.main.config;

import br.gov.to.secad.api.ergon.config.ErgonRestClient;
import br.gov.to.secad.api.ergon.enumerator.ErgonRestMethods;
import br.gov.to.secad.api.ergon.xml.DadosSimplesResponseXml;
import br.gov.to.secad.api.ergon.xml.ServidorRequestXml;
import br.gov.to.secad.api.main.enumerator.RestMethods;
import br.gov.to.secad.api.main.enumerator.RestResponseStatus;
import br.gov.to.secad.api.main.xml.UsuarioRequestXml;
import br.gov.to.secad.api.main.xml.UsuarioResponseXml;
import br.gov.to.secad.main.domain.Usuario;
import br.gov.to.secad.main.util.MessagesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wandyer.silva
 */
@Component
@PropertySource(value = "/WEB-INF/application.yml", ignoreResourceNotFound = true)
public class RestClient {

	private ClientConfig clientConfig;
	private Client client;
	private WebTarget webTarget;
	private Invocation.Builder invocationBuilder;
	private Response response;

	//URL de conexão
	@Value("${rest.authUrl}")
	private String restAuthUrl;

	@Value("${rest.servicesUrl}")
	private String restServiceUrl;

	@Value("${rest.hashKey}")
	private String hashKey;

	//Json Serializer/Deserializer
	private DateFormat df;
	private ObjectMapper mapper;

	private String token;

	public RestClient() {

	}

	@PostConstruct
	public void init() { //Qualquer alteração, é necessário reiniciar o projeto
		clientConfig = new ClientConfig()
				.property(ClientProperties.READ_TIMEOUT, 30000)
				.property(ClientProperties.CONNECT_TIMEOUT, 5000);

		client = ClientBuilder.newClient(clientConfig);

		mapper = new ObjectMapper();
		df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(df);
	}

	public boolean authenticateUser(String cpf, String senhaHash) {
		if (requestToken()) {
			try {
				UsuarioRequestXml usuarioRequest = new UsuarioRequestXml(cpf, senhaHash);

				webTarget = client
						.target(restAuthUrl)
						.path(RestMethods.LOGIN.getLabel());

				invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
						.header("token", token);

				String json = mapper.writeValueAsString(usuarioRequest);

				response = invocationBuilder.post(Entity.json(json));

				if (response.getStatus() == 200) { //Sucesso
					response.bufferEntity();

					String statusReturn = response.readEntity(String.class);
					if (statusReturn.equalsIgnoreCase(RestResponseStatus.SUCESSO.toString())) {
						return true;
					} else {
						return false;
					}
				} else {
					if (response.getStatus() == 401) { //Token inválido
//						messages.addError("Erro de autenticação");
						return false;
					}

					if (response.getStatus() == 204) { //Usuário não encontrado
//						messages.addError("CPF ou Senha incorretos");
						return false;
					}

					if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
//						messages.addError("Serviço não disponível");
						return false;
					}

					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}
			} catch (JsonProcessingException ex) {
				Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	public Usuario findUser(String cpf) {
		if (requestToken()) {
			try {
				UsuarioRequestXml usuarioRequest = new UsuarioRequestXml(cpf);

				webTarget = client
						.target(restServiceUrl)
						.path(RestMethods.USER.getLabel());

				invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
						.header("token", token);

				String json = mapper.writeValueAsString(usuarioRequest);

				response = invocationBuilder.post(Entity.json(json));

				if (response.getStatus() == 200) {
					UsuarioResponseXml usuarioRes = response.readEntity(UsuarioResponseXml.class);
					Usuario usuario = new Usuario(usuarioRes);
					return usuario;
				} else {
					if (response.getStatus() == 401) { //Token inválido
//						messages.addError("Erro de autenticação");
						return null;
					}

					if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
//						messages.addError("Serviço não disponível");
						return null;
					}

					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}
			} catch (JsonProcessingException ex) {
				Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	public DadosSimplesResponseXml findDadosSimples(ServidorRequestXml servidorRequest) {
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

						DadosSimplesResponseXml dadosSimplesResponseXml = mapper.readValue(
								jsonString, DadosSimplesResponseXml.class);
						return dadosSimplesResponseXml;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					if (response.getStatus() == 401) { //Token inválido
//						messages.addError("Erro de autenticação");
						return null;
					}

					if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
//						messages.addError("Serviço não disponível");
						return null;
					}

					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}
			} catch (JsonProcessingException ex) {
				Logger.getLogger(ErgonRestClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	private Boolean requestToken() {
		try {
			webTarget = client
					.target(restAuthUrl)
					.path(RestMethods.TOKEN.getLabel());

			invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("hashkey", hashKey);

			response = invocationBuilder.get();

			if (response.getStatus() == 200) {
				response.bufferEntity();
				this.token = response.readEntity(String.class);
				return true;
			} else {

				if (response.getStatus() == 404) { //WebService não disponível ou não encontrado
//					messages.addError("Serviço não disponível");
					return false;
				}


				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
		} catch (Exception e) {
			Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, e);
//			messages.addError("Serviço não disponível");
			return false;
		}

	}

	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
