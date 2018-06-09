package br.gov.to.secad.main.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author flavio.madureira
 */
public class DateUtil {

	public static LocalDate dateToLocalDate(Date data) {
		if (data != null) {
			if (data.getClass().getName().equals(Date.class.getName())) {
				return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			} else if (data.getClass().getName().equals(java.sql.Date.class.getName())) {
				Date utilDate = new Date(data.getTime());
				return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
		}
		return null;
	}
}
