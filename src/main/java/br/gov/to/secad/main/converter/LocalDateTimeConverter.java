package br.gov.to.secad.main.converter;

import org.springframework.stereotype.Service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Locale BRAZIL = new Locale("pt", "BR");
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withLocale(BRAZIL));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        LocalDateTime dateValue = (LocalDateTime) value;
        String valor =  dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return valor;
    }
}

