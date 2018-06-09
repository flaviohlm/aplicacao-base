package br.gov.to.secad.main.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe que é chamada caso ocorra alguma falha no login.
 *
 * @author wandyer.silva
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // Adicionar exceção do Spring Security na sessão para ser reconhecido no LogPhaseListener.java
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        }

        // Retornar à página de login
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
