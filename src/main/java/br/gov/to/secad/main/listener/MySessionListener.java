package br.gov.to.secad.main.listener;

import br.gov.to.secad.main.util.DeleteFileFolder;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;

/**
 *
 * @author wandyer.silva
 */
public class MySessionListener implements HttpSessionListener {

    public MySessionListener() {
    }

    /**
     * Método que é chamado quando a sessão do usuário for criada.
     * @param event .
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
    }

    /**
     * Método que é chamado quando a sessão do usuário for fechada.
     * @param event .
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        deleteFilePaths(event.getSession());
    }

    private void deleteFilePaths(HttpSession session) {
        //Remover arquivos/diretórios criado nesta Sessão
        String sessionId = session.getId();

        String deletePathPortal = session.getServletContext().getRealPath("") + "resources" + File.separator  + "temp" + File.separator;

        String deletePathPep = session.getServletContext().getRealPath("") + "resources" + File.separator  + "temp" + File.separator + "pep" + File.separator;

        deletePathPortal += sessionId +  File.separator;
        deletePathPep += sessionId + File.separator;

        new DeleteFileFolder(deletePathPortal);
        new DeleteFileFolder(deletePathPep);
    }

}
