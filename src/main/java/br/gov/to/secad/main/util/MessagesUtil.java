
package br.gov.to.secad.main.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author wandyer.silva
 */
public class MessagesUtil {

    private static void add(String message, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage(message,"");
        msg.setSeverity(severity);

        context.addMessage("", msg);
    }

    public static void addInfo(String msg) {
        add(msg, FacesMessage.SEVERITY_INFO);
    }
    public static void addWarn(String msg) {
        add(msg, FacesMessage.SEVERITY_WARN);
    }
    public static void addError(String msg) {
        add(msg, FacesMessage.SEVERITY_ERROR);
    }
    public static void addFatal(String msg) {
        add(msg, FacesMessage.SEVERITY_FATAL);
    }  
}
