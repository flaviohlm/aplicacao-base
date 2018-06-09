package br.gov.to.secad.main.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omnifaces.util.Faces;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 *
 * @author wandyer.silva
 */
@Service
public final class Redirector {

    private static Logger logger = LogManager.getLogger();

    public static void redirect(String uri)  {
        try {
            Faces.redirect(Faces.getRequestContextPath() + uri);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
