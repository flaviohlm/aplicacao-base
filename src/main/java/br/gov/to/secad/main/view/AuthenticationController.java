package br.gov.to.secad.main.view;

import br.gov.to.secad.main.domain.Menu;
import br.gov.to.secad.main.domain.Perfil;
import br.gov.to.secad.main.service.MenuService;
import br.gov.to.secad.main.service.PerfilService;
import br.gov.to.secad.main.security.UsuarioSistema;
import br.gov.to.secad.main.security.provider.AuthenticationProvider;
import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import br.gov.to.secad.main.util.Redirector;
import br.gov.to.secad.main.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * @author wandyer.silva
 */
@ManagedBean
@SessionScoped
public class AuthenticationController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LogManager.getLogger();

    @ManagedProperty(value = "#{menuService}")
    private MenuService menuService;

    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty(value = "#{perfilService}")
    private PerfilService perfilService;

    @ManagedProperty(value = "#{authenticationProvider}")
    private AuthenticationProvider authenticationProvider;

    private UsuarioSistema usuarioSistema;

    private List<Menu> listaMenusAcesso = new ArrayList<>();

    private Perfil perfilAtual = new Perfil();

    private Perfil perfilSelectAux;

    private boolean logadoMatriculaAntiga = false;

    private boolean servidorInativo = false;

    /**
     * Método que é chamado após o login do usuário for realizado com sucesso.
     * Para buscar e armazenar na sessão o usuário logado, o perfil atual e um
     * vínculo padrão.
     */
    @PostConstruct
    public void init() {
        try {
            if (usuarioSistema == null) {
                usuarioSistema = SpringSecurityUtil.getCurrentPrincipal();

                if (usuarioSistema == null) {
                    return;
                }

                //this.perfilAtual = perfilService.findByNome("Usuário Seleção");

                popularMenus();

            }
        } catch (NullPointerException ex) {
            logger.error(ex);
            logger.error(SpringSecurityUtil.getCPF());
        }
    }

    /**
     * Método que fará a consulta dos menus que o usuário tem permissão
     */
    private void popularMenus() {
        listaMenusAcesso = menuService.findByPerfil(perfilAtual.getId());
    }

    /**
     * Método para buscar o nome do perfil e retornar uma coleção de
     * GrantedAuthority
     *
     * @param perfil .
     * @return .
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Perfil perfil) {
        List<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(perfil.getNome()));
        return result;
    }

    /**
     * Método para verificar se o usuário está autenticado
     *
     * @return .
     */
    public boolean isAuthenticated() {
        return SpringSecurityUtil.isAuthenticated();
    }

    /**
     * Método para realizar o logout do usuário
     */
    public void logout() {
        Redirector.redirect("/logout");
    }

    public static AuthenticationController getCurrentInstance() {
        return (AuthenticationController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authenticationController");
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public List<Menu> getListaMenusAcesso() {
        return listaMenusAcesso;
    }

    public void setListaMenusAcesso(List<Menu> listaMenusAcesso) {
        this.listaMenusAcesso = listaMenusAcesso;
    }

    public boolean isLogadoMatriculaAntiga() {
        return logadoMatriculaAntiga;
    }

    public void setLogadoMatriculaAntiga(boolean logadoMatriculaAntiga) {
        this.logadoMatriculaAntiga = logadoMatriculaAntiga;
    }

    public Perfil getPerfilAtual() {
        return perfilAtual;
    }

    public void setPerfilAtual(Perfil perfilAtual) {
        this.perfilAtual = perfilAtual;
    }

    public PerfilService getPerfilService() {
        return perfilService;
    }

    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public Perfil getPerfilSelectAux() {
        return perfilSelectAux;
    }

    public void setPerfilSelectAux(Perfil perfilSelectAux) {
        this.perfilSelectAux = perfilSelectAux;
    }

    public boolean isServidorInativo() {
        return servidorInativo;
    }

    public void setServidorInativo(boolean servidorInativo) {
        this.servidorInativo = servidorInativo;
    }
}
