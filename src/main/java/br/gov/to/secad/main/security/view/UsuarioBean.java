package br.gov.to.secad.main.security.view;

import br.gov.to.secad.main.domain.Perfil;
import br.gov.to.secad.main.domain.Pessoa;
import br.gov.to.secad.main.domain.Usuario;
import br.gov.to.secad.main.service.PerfilService;
import br.gov.to.secad.main.service.PessoaService;
import br.gov.to.secad.main.service.UsuarioService;
import br.gov.to.secad.main.util.FacesMessages;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author wandyer.silva
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty(value = "#{perfilService}")
    private PerfilService perfilService;

    @ManagedProperty(value = "#{pessoaService}")
    private PessoaService pessoaService;

    private FacesMessages messages = new FacesMessages();

    private Integer idElemento;
    private Usuario usuario = new Usuario();
    //private List<Usuario> listaUsuarios = new ArrayList<>();
    private LazyDataModel<Usuario> listaUsuarios;
    private List<Perfil> listaPerfis = new ArrayList<>();

    private Boolean showDadosServidor = false;
    private Boolean showConsultaDados = false;
    private Boolean showOrgaos = false;
    private Boolean showUnidades = false;
    private Boolean showQuadros = false;
    private Boolean disableSave = true;

    private List<Usuario> listaUsuariosCadastrar = new ArrayList<>();
    private List<Perfil> listaPerfisSelecionados = new ArrayList<>();
    

    public UsuarioBean() {
        //lbUsuarioLogado = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
    }

    @PostConstruct
    public void init() {
        //listaUsuarios = new LazyUsuario(this.getUsuarioService(), lbUsuarioLogado);
    }

    public String salvar() throws NoSuchAlgorithmException {

        for (Usuario u : listaUsuariosCadastrar) {

            try {
                if (!listaPerfisSelecionados.isEmpty()) {
                    u.setListaPerfis(listaPerfisSelecionados);
                }

                if (u.getPessoa().getId() != null) {
                    usuarioService.saveUsuario(u);
                } else {
                    pessoaService.savePessoa(u.getPessoa());
                    usuarioService.saveUsuario(u);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("UsuarioBean-> salvar");
                messages.error("Erro ao salvar o usuário.");
                return "";
            }
        }
        messages.info("Usuário(s) salvo(s) com sucesso.");
        return "/configuracoes/usuario/index?faces-redirect=true";
    }

    public String atualizar() throws NoSuchAlgorithmException {

        if (usuario.getPessoa().getCpf().equals("02968807102")) {
            messages.error("Erro ao salvar o usuário. (6)");
            return "";
        }

        this.setaMenorNivelPerfil();

        if (usuario.getPerfilAtual() == null) {
            messages.error("Selecione um perfil para o usuário.");
            return "";
        }

//        if (lbUsuarioLogado.getPerfilAtual().getNivel() > usuario.getPerfilAtual().getNivel()) {
//            messages.error("Você não tem permissão para alterar esse usuário.");
//            return "";
//        }

        try {

            if (usuario.getPessoa().getId() != null) {
                usuarioService.saveUsuario(usuario);

            } else {
                pessoaService.savePessoa(usuario.getPessoa());
                usuarioService.saveUsuario(usuario);
            }

            messages.info("Usuário salvo com sucesso.");
            return "/configuracoes/usuario/index?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("UsuarioBean-> salvar");
            messages.error("Erro ao salvar o usuário.");
        }

        return "";
    }

    public void mudarSituacao(Usuario u) {
        try {
            usuarioService.saveUsuario(u);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("UsuarioBean-> salvar");
            messages.error("Erro ao salvar os dados.");
        }
    }

    public void setaMenorNivelPerfil() {
        if (usuario.getListaPerfis().isEmpty()) {
            return;
        }
        usuario.setPerfilAtual(usuario.getListaPerfis().get(0));
        for (Perfil p : usuario.getListaPerfis()) {
            for (Perfil q : usuario.getListaPerfis()) {
                if (q.getNivel() < p.getNivel()) {
                    usuario.setPerfilAtual(q);
                }
            }
        }
    }

    public String consultarDadosServidor() {

        if (usuario.getPessoa().getCpf().isEmpty()) {
            messages.error("Campo 'CPF' é de preenchimento obrigatório.");
            return "";
        }

        try {

            String cpf = usuario.getPessoa().getCpf();

            usuario = usuarioService.findByCpf(usuario.getPessoa().getCpf());

            if (usuario == null) { //Usuário não está cadastrado ainda
                usuario = new Usuario();

                Pessoa pessoa = pessoaService.findOnePessoa(1);// pessoaService.findByCpf(cpf);

                if (pessoa == null) {
                    usuario.getPessoa().setCpf(cpf);

                    //Consultar Ergon
                    usuario = usuarioService.preencherDadosUsuario(usuario);

                    if (usuario != null) {
                        showDadosServidor = true;
                    } else { //CPF não cadastrado no Ergon
                        messages.error("CPF não cadastrado na base do Estado.");
                    }
                } else {
                    showDadosServidor = true;
                    usuario.setPessoa(pessoa);
                    usuario = usuarioService.preencherDadosUsuario(usuario);
                }
                listaUsuariosCadastrar.add(usuario);
                usuario = new Usuario();
            } else {
                return "/configuracoes/usuario/editar/index?id=" + usuario.getId() + "&faces-redirect=true";
            }

        } catch (Exception e) {
            messages.error("Erro ao consultar servidor.");
            System.out.println("ERRO: " + e.getLocalizedMessage());
        }

        return "";
    }

    public void selecionarPerfilListener() {
        
    }

    public void selecionarPerfilBlocoListener() {
        
    }

    public void selecionarUnidadeListener() {
       
    }

    public String limpar() {
        this.usuario = new Usuario();
        showDadosServidor = false;

        return "/configuracoes/usuario/edit?faces-redirect=true";
    }

    public void delete() {
        try {
            usuario.setExcluido(true);
            usuario.setAtivo(false);
            usuarioService.saveUsuario(usuario);
            this.init();
            usuario = null;
            messages.info("Usuario excluído com sucesso.");
        } catch (Exception ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, "LOG: ", ex);
            messages.error("Não foi possível excluir este Usuário.");
        }
    }

    //CARREGA MODEL
    public void loadModel() {
        if (idElemento != null) {
            showConsultaDados = false;
            showDadosServidor = true;
            disableSave = false;

            usuario = usuarioService.findOneUsuario(idElemento);

            if (usuario == null) {
                messages.error("Usuário não encontrado.");
                return;
            }

            //usuario = usuarioService.preencherDadosUsuario(usuario);
            if (usuario == null) {
                messages.error("CPF não cadastrado na base do Estado.");
            }

        } else {
            showConsultaDados = true;
            showDadosServidor = false;
            disableSave = true;
        }
    }

    //IR PARA CADASTRO -- APAGAR
    public String gotoUsuarioEdit(Integer idUsuario) {
        usuario = new Usuario();
        return "/configuracoes/usuario/editar/index?id=" + idUsuario + "&faces-redirect=true";
    }

    public String gotoUsuarioNew() {
        usuario = new Usuario();
        return "/configuracoes/usuario/cadastrar/index?faces-redirect=true";
    }

    public String gotoMeuUsuario() {
        Integer idUsuario = 0;//LoginBean.getCurrentInstance().getUsuarioLogado().getId();
        return "/configuracoes/usuario/editar/index?idElemento=" + idUsuario + "&faces-redirect=true";
    }

    public void atualizarPessoasComErgon() {        
    }

    //GETTERS AND SETTERS
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public PerfilService getPerfilService() {
        return perfilService;
    }

    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public FacesMessages getMessages() {
        return messages;
    }

    public void setMessages(FacesMessages messages) {
        this.messages = messages;
    }

    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LazyDataModel<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(LazyDataModel<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Perfil> getListaPerfis() {
//        if (lbUsuarioLogado.getPerfilAtual().getNivel() < 0) {
//            listaPerfis = perfilService.findAll(-1);
//        } else {
//            listaPerfis = perfilService.findAllPerfil(lbUsuarioLogado.getPerfilAtual().getNivel());
//        }
        return listaPerfis;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public Boolean getShowDadosServidor() {
        return showDadosServidor;
    }

    public void setShowDadosServidor(Boolean showDadosServidor) {
        this.showDadosServidor = showDadosServidor;
    }

    public Boolean getDisableSave() {
        return disableSave;
    }

    public void setDisableSave(Boolean disableSave) {
        this.disableSave = disableSave;
    }

    public Boolean getShowConsultaDados() {
        return showConsultaDados;
    }

    public void setShowConsultaDados(Boolean showConsultaDados) {
        this.showConsultaDados = showConsultaDados;
    }

    public List<Usuario> getListaUsuariosCadastrar() {
        return listaUsuariosCadastrar;
    }

    public void setListaUsuariosCadastrar(List<Usuario> listaUsuariosCadastrar) {
        this.listaUsuariosCadastrar = listaUsuariosCadastrar;
    }

    public List<Perfil> getListaPerfisSelecionados() {
        return listaPerfisSelecionados;
    }

    public void setListaPerfisSelecionados(List<Perfil> listaPerfisSelecionados) {
        this.listaPerfisSelecionados = listaPerfisSelecionados;
    }

    public Boolean getShowOrgaos() {
        return showOrgaos;
    }

    public void setShowOrgaos(Boolean showOrgaos) {
        this.showOrgaos = showOrgaos;
    }

    public Boolean getShowUnidades() {
        return showUnidades;
    }

    public void setShowUnidades(Boolean showUnidades) {
        this.showUnidades = showUnidades;
    }

    public Boolean getShowQuadros() {
        return showQuadros;
    }

    public void setShowQuadros(Boolean showQuadros) {
        this.showQuadros = showQuadros;
    }

}
