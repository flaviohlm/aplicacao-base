package br.gov.to.secad.main.listener;

import br.gov.to.secad.main.util.DeleteFileFolder;
import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author wandyer.silva
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Do stuff during webapp startup.
        deleteFilePaths(event.getServletContext().getRealPath(""));
    }

    private void deleteFilePaths(String realPath) {
        String pathPortal = realPath + "resources" + File.separator  + "temp" ;

        String pathPep = realPath + "resources" + File.separator  + "temp" + File.separator + "pep";

        String deletePathPortal = pathPortal.replace("\\", File.separator);
        String deletePathPep = pathPep.replace("\\", File.separator);

        new DeleteFileFolder(deletePathPortal);
        new DeleteFileFolder(deletePathPep);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp shutdown.
    }

}
