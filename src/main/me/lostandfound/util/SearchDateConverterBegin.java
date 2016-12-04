package me.lostandfound.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.log.Log;
import org.jboss.seam.log.Logging;

/**
 * Conversion de date pour les paramètres dans les fichier .page.xml
 * 
 * See https://jira.jboss.org/jira/browse/JBSEAM-4413
 * 
 * @author jkronegg
 * 
 */
public class SearchDateConverterBegin implements Converter {

	static Log log = Logging.getLog(SearchDateConverterBegin.class);
	static SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ toString() + "日期end" + arg2);
		try {
			Date start = df1.parse(arg2);
			Calendar c = Calendar.getInstance();
			c.setTime(start);
			c.add(java.util.Calendar.HOUR_OF_DAY, 0);
			c.add(Calendar.MINUTE, 0);
			c.add(Calendar.SECOND, 0);
			return c.getTime();
		} catch (ParseException e) {
			log.warn("Impossible to convert : " + arg2);
			return null;
		}
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try {
			return df1.format(arg2);
		} catch (IllegalArgumentException e) {
			log.warn("Impossible to convert : " + arg2.toString());
			return null;
		}
	}
}