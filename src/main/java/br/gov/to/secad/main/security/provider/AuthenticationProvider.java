package br.gov.to.secad.main.security.provider;

import br.gov.to.secad.main.domain.Usuario;
import br.gov.to.secad.main.security.UsuarioSistema;
import br.gov.to.secad.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author wandyer.silva
 */
@Service
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    public AuthenticationProvider(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private UsuarioService usuarioService;

    /**
     * Autenticar o usuário
     *
     * @param s .
     * @param auth .
     * @return userDetails com as credenciais de autenticação
     * @throws AuthenticationException .
     */
    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken auth) throws AuthenticationException {

        Usuario usuario = usuarioService.findByCpf(auth.getPrincipal().toString());
        String password = auth.getCredentials().toString();

        if (usuario == null) {
            throw new BadCredentialsException("Usuário não encontrado");
        }

        try {
            usuario.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new UsuarioSistema(usuario.getPessoa().getCpf(), usuario.getPassword(), auth.getAuthorities(), usuario);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }
}
