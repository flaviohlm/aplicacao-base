package br.gov.to.secad.main.security.provider;

import br.gov.to.secad.api.main.config.RestClient;
import br.gov.to.secad.main.domain.Pessoa;
import br.gov.to.secad.main.security.UsuarioSistema;
import br.gov.to.secad.main.domain.Usuario;
import br.gov.to.secad.main.service.UsuarioService;
import br.gov.to.secad.main.util.CpfUtil;
import br.gov.to.secad.main.util.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wandyer.silva
 */
@Service
public class DefaultAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private RestClient restClient;

    private UsuarioService usuarioService;

    private static Logger logger = LogManager.getLogger();

    @Autowired
    public DefaultAuthenticationProvider(RestClient restClient, UsuarioService usuarioService) {
        this.restClient = restClient;
        this.usuarioService = usuarioService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }

    /**
     * Método que faz a primeira autenticação do usuário ao logar.
     *
     * @param s .
     * @param auth dados de autenticação do formulário na tela de login
     * @return retorna a entidade UsuarioSistema para o spring security
     * @throws AuthenticationException .
     */
    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken auth) throws AuthenticationException {

        try {
            if (Objects.equals(auth.getName(), "") || Objects.equals(auth.getCredentials(), "")) {
                logger.info("CPF ou senha vazios");
                throw new BadCredentialsException("Por favor, informe seu CPF e senha");
            }

            String cpf = auth.getName().replaceAll("\\.", "").replace("-", "");

            if (!CpfUtil.isCPF(cpf)) {
                logger.info("CPF inválido");
                throw new BadCredentialsException("CPF inválido");
            }

            String password = PasswordUtil.md5(auth.getCredentials().toString());
            Usuario usuario = usuarioService.findByCpf(cpf);
            
            if (usuario != null) {
                if (restClient.authenticateUser(cpf, password)) {
                    usuario = new Usuario();
                    usuario.setPessoa(new Pessoa());
                    usuario.getPessoa().setCpf(cpf);
                    
                    usuario = usuarioService.preencherDadosUsuarioCompleto(usuario);

                    //usuarioService.saveUsuario(usuario);
                } else {
                    logger.info("CPF ou Senha incorretos");
                    throw new BadCredentialsException("CPF ou Senha incorretos");
                }

            } else {
                logger.error("Usuário não existe");
            }

            usuario.setPassword(password);
            return new UsuarioSistema(usuario.getPessoa().getCpf(), usuario.getPassword(), getAuthorities(usuario), usuario);

        } catch (Exception ex) {
            if (ex instanceof BadCredentialsException) {
                logger.info(ex);
            } else {
                logger.error(ex);
            }
            throw new BadCredentialsException(ex.getLocalizedMessage());
        }

    }

    public void verificarServidor() {

    }

    /**
     * Este método irá buscar um perfil do usuário para usar como permissão,
     * caso o usuário decida não selecionar um perfil.
     *
     * @param usuario entidade usuário
     * @return retorna uma lista de permissão do spring security
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        List<GrantedAuthority> result = new ArrayList<>();

        // Servidor normal, sem perfil
        //result.add(new SimpleGrantedAuthority("Usuário Seleção"));

        return result;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }
}
