package br.gov.to.secad.main.exception;

import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.omnifaces.util.Faces;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.event.ExceptionQueuedEvent;
import java.io.IOException;
import java.util.Iterator;

/**
 * Classe para tratar globalmente as exceções do sistema
 *
 * @author wandyer.silva
 */
public class JsfExceptionViewExpiredHandler extends FullAjaxExceptionHandler {

    private static Logger logger = LogManager.getLogger();

    JsfExceptionViewExpiredHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            Throwable exception = event.getContext().getException();
            try {
                if (exception instanceof ViewExpiredException) {
                    if (!Faces.getResponse().isCommitted()) {
                        Faces.redirect(Faces.getRequestContextPath() + "/erro/sessao/index.xhtml");
                        Faces.responseComplete();
                    }
                } else if (exception instanceof FacesException) {
                    if (!Faces.getResponse().isCommitted()) {
                        Faces.redirect(Faces.getRequestContextPath() + "/erro/500/index.xhtml");
                        Faces.responseComplete();
                    }
                    logger.error(exception);
                    logger.error(SpringSecurityUtil.getCPF());
                } else {
                    if (!Faces.getResponse().isCommitted()) {
                        Faces.redirect(Faces.getRequestContextPath() + "/erro/500/index.xhtml");
                        Faces.responseComplete();
                    }
                    logger.error(exception);
                    logger.error(SpringSecurityUtil.getCPF());
                }
            } catch (IOException e) {
                logger.error(e);
                logger.error(SpringSecurityUtil.getCPF());
            } finally {
                events.remove();
            }
        }
        getWrapped().handle();
    }

}
