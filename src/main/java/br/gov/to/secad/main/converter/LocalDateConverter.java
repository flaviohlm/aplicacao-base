package br.gov.to.secad.main.converter;

import org.springframework.stereotype.Service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service(value = "localDateConverter")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Locale BRAZIL = new Locale("pt", "BR");
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        LocalDate dateValue = (LocalDate) value;
        Locale BRAZIL = new Locale("pt", "BR");
        return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
    }
}