package br.gov.to.secad.main.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 *
 * @author wandyer.silva
 */
public class FileHelper {


    /**
     * Método para retornar o caminho real temporário que poderá ser usado para salvar arquivos durante
     * downloads ou upload de imagens.
     *
     * @return .
     */
    public static String tempResourceRealPath() {
        String source;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + "resources" + File.separator  + "temp";
        source = newFileName.replace("\\", File.separator);
        return source + File.separator;
    }

    /**
     * Método para retornar o caminho temporário em request
     *
     * @return .
     */
    public static String tempResourceRequestPath() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        String requestUrl = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
        return requestUrl + "resources/temp/";
    }

    
    /**
     *  Método para retornar o ID da Sessão atual. 
     * 
     * @return String com sessionId
     */
    public static String mySessionID() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        return session.getId();
    }
}
