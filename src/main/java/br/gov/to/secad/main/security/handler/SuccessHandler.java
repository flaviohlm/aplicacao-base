package br.gov.to.secad.main.security.handler;

import br.gov.to.secad.api.main.enumerator.MainRestMethods;
import br.gov.to.secad.main.security.UsuarioSistema;
import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Optional;

/**
 * Classe que é chamada após ter o sucesso no login. Permite fazer
 * redirecionamentos específicos.
 *
 * @author wandyer.silva
 */
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    private static Logger logger = LogManager.getLogger();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String targetUrl = determineTargetUrl(request);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Definir lógica para decidir qual página redirecionar o usuário
     *
     * @return página para redirecionar
     */
    private String determineTargetUrl(HttpServletRequest request) {
        UsuarioSistema usuarioSistema = SpringSecurityUtil.getCurrentPrincipal();

        String returnAPI = verificarConexaoAPI(request);
        
        if (returnAPI != null) {
            return returnAPI;
        }

        if (usuarioSistema == null) {
            return "/index.xhtml";
        }
        
        return "/index.xhtml";
    }

    private String verificarConexaoAPI(HttpServletRequest request) {
        try {

            Environment env = ContextLoader.getCurrentWebApplicationContext().getEnvironment();

            URL url = new URL(env.getProperty("rest.servicesUrl") + "/" + MainRestMethods.STATUS.getLabel());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(20000);
            con.setRequestMethod("GET");

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) con.getContent()));
            JsonObject rootobj = root.getAsJsonObject();

            if (con.getResponseCode() != 200) {
                HttpSession session = request.getSession(false);
                session.setAttribute("API-ERROR", new SecurityException("Serviço indisponível no momento."));
                return "/login/index.xhtml";
            }

            String ergon = Optional.ofNullable(rootobj.get("Ergon"))
                    .map(JsonElement::toString)
                    .orElse(null);

            if (ergon == null) {
                HttpSession session = request.getSession(false);
                session.setAttribute("API-ERROR", new SecurityException("Conexão com o Ergon indisponível."));
                return "/login/index.xhtml";
            }

            ergon = ergon.replace("\"", "");

            if (con.getResponseCode() != 200 || !ergon.equalsIgnoreCase("on")) {
                HttpSession session = request.getSession(false);
                session.setAttribute("API-ERROR", new SecurityException("Conexão com o Ergon indisponível."));
                return "/login/index.xhtml";
            }

        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                HttpSession session = request.getSession(false);
                session.setAttribute("API-ERROR", new SecurityException("Serviço indisponível no momento."));
            }

            logger.error(e);
            logger.error(SpringSecurityUtil.getCPF());
            return "/login/index.xhtml";
        }

        return null;
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
