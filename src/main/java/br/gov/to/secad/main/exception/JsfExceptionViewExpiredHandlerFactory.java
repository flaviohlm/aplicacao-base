package br.gov.to.secad.main.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JsfExceptionViewExpiredHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory wrapped;

    public JsfExceptionViewExpiredHandlerFactory(ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionViewExpiredHandler(getWrapped().getExceptionHandler());
    }

    @Override
    public ExceptionHandlerFactory getWrapped() {
        return wrapped;
    }

}
