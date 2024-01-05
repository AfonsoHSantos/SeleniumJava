package afonso.h.santos.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.cglib.core.TinyBitSet;

public class DateUtils {

	public Date getFutureDateByDays(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}
	
	public String getFormatedDate(Date date) {
		DateFormat format = new SimpleDateFormat("dd/MM/YYY");
		return format.format(date);
	}
		
}
