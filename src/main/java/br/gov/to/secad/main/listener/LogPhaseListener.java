package br.gov.to.secad.main.listener;

import br.gov.to.secad.main.util.MessagesUtil;
import br.gov.to.secad.main.util.Redirector;
import br.gov.to.secad.main.view.AuthenticationController;
import br.gov.to.secad.ultima.menu.MenuView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wandyer.silva
 */
public class LogPhaseListener implements PhaseListener {

	private static Logger logger = LogManager.getLogger();

	private static final String errorpage = "/erro/sessao/index.xhtml";

	@Override
	public void afterPhase(PhaseEvent pe) {
	}


	@Override
	public void beforePhase(PhaseEvent event) {

		if (event.getPhaseId().getOrdinal() == 6) {
			checkMenus(event.getFacesContext());
		}

		checkSpringExceptions();

		FacesContext context = event.getFacesContext();
		ExternalContext ext = context.getExternalContext();
		HttpSession session = (HttpSession) ext.getSession(false);
		boolean newSession = (session == null) || (session.isNew());
		boolean postback = !ext.getRequestParameterMap().isEmpty();
		boolean timedout = postback && newSession;

		if (timedout) {
			logger.debug("Request Timedout");
			redirect();
		}

		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.getPartialViewContext().isAjaxRequest() || facesContext.getRenderResponse()) { // not ajax or too late
			return;
		}

		final HttpServletRequest request = HttpServletRequest.class.cast(facesContext.getExternalContext().getRequest());
		if (request.getDispatcherType() == DispatcherType.FORWARD && errorpage.equals(request.getServletPath())) { // isLoginRedirection()
			final String redirect = facesContext.getExternalContext().getRequestContextPath() + request.getServletPath();
			try {
				logger.debug("Redirecting.");
				facesContext.getExternalContext().redirect(redirect);
			} catch (final IOException e) {
				logger.error(e);
			}
		}
	}

	public void redirect() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ec.getResponse();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		try {
			response.setCharacterEncoding(request.getCharacterEncoding());

			RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);

			RenderKit renderKit = factory.getRenderKit(fc,
					fc.getApplication().getViewHandler().calculateRenderKitId(fc));

			ResponseWriter responseWriter
					= renderKit.createResponseWriter(
					response.getWriter(), null, request.getCharacterEncoding());
			fc.setResponseWriter(responseWriter);

			ec.redirect(ec.getRequestContextPath() + errorpage);

			Application app = fc.getApplication();
			ViewHandler viewHandler = app.getViewHandler();
			UIViewRoot view = viewHandler.createView(fc, errorpage);
			fc.setViewRoot(view);
			fc.renderResponse();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	private void checkSpringExceptions() {
		Exception ex = (Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(
						WebAttributes.AUTHENTICATION_EXCEPTION);

		Exception ex2 = (Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(
						"API-ERROR");

		if (ex instanceof BadCredentialsException) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					WebAttributes.AUTHENTICATION_EXCEPTION, null);
			MessagesUtil.addError(ex.getMessage());
		}

		if (ex2 instanceof SecurityException) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					"API-ERROR", null);
			MessagesUtil.addError(ex2.getMessage());
			Redirector.redirect("/logout");
		}
	}

	private void checkMenus(FacesContext context) {
		if (AuthenticationController.getCurrentInstance() != null) {
			String viewId = context.getViewRoot().getViewId();
			String urlArray[] = viewId.split("/");

			if (MenuView.getCurrentInstance() != null) {
				MenuView.getCurrentInstance().createMenuModel();
			}
		}
	}

	// Qual fase o Listener atende
	@Override
	public PhaseId getPhaseId() {
		//Retorna todas as fases
		return PhaseId.ANY_PHASE;
	}

}
