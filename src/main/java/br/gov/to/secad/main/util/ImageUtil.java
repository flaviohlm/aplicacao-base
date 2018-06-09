package br.gov.to.secad.main.util;

import br.gov.to.secad.main.view.AuthenticationController;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;

/**
 * @author wandyer.silva
 */
@ManagedBean
@SessionScoped
public class ImageUtil {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authenticationController;

    public ImageUtil() {
    }

    public StreamedContent getFotoPerfil() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        else {

            // Não é possível buscar informações de do Spring Security Context neste cicle de vida, por isso é necessário buscar
            // do AuthenticationController

            if (authenticationController.getUsuarioSistema() == null) {
                return new DefaultStreamedContent(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/img/profile-avatar.png"));
            }

//            if (authenticationController.getUsuarioSistema().getUsuario().getFoto() == null) {
//                return new DefaultStreamedContent(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/img/profile-avatar.png"));
//            }

            return new DefaultStreamedContent(new ByteArrayInputStream(null));
        }
    }

    public AuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    public void setAuthenticationController(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }
}
