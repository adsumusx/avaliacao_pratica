package br.desafio.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import static java.util.Calendar.*;

public class DateHelper {
	long sumDias;

	public DateHelper() {
		super();
	}

	// na listagem do certameDao tem um metodo que atualiza o certame,
	// e quando vamos comparar data com a data atual ele considera hora zero
	// aqui juntamos a data e a hora para comparar
	public static Date dateAndTimeToDateTime(Date dateReceived, String hour) throws ParseException {

		try {			
			
			String[] aHour = hour.split(":");
			
			DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		    Date date;
		    date = (Date)formatter.parse(dateReceived.toString());

		    	
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    
		    cal.set(Calendar.HOUR, Integer.parseInt(aHour[0]));
		    cal.set(Calendar.MINUTE, Integer.parseInt(aHour[1]));
		    //cal.set(Calendar.SECOND, Integer.parseInt(aHour[2]));
		    
		   Date oDate =  cal.getTime();
			
			return oDate;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	public static Date dataTeste(String dateReceived, String hour) throws ParseException {

		try {
			// junta data com hora e devolve com o formato yyyy/MM/dd HH:mm:ss
			// utilizado para comparação da dataTime do certame
			SimpleDateFormat formatReceived = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatSend = new SimpleDateFormat("yyyy/MM/dd");
			Date date = formatReceived.parse(dateReceived.toString());
			String sDateTime = formatSend.format(date);
			sDateTime = sDateTime + " " + hour + ":00";
			DateHelper dateHelperAccess = new DateHelper();
			Date sDate = dateHelperAccess.getStringReturnDateBar(sDateTime);
			return sDate;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static Date date(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
			return sdf.parse(date);
		} catch (ParseException w) {
			w.printStackTrace();
			return null;
		}
	}

	public static Date objDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date dateWithoutTime(Date dateReceived) {
		// REMOVE AS HORAS E RETORNA APENAS A DATA
		// USO ISSO NO CERTAMEDAO POIS NA HORA DE ATUALIZAR O STATUS DO CERTAME ELE VEM
		// COM FORMATO DE DATA E HORA
		// MAS A HORA É SALVA DE FORMA SEPARADA NO BANCO DE DADOS
		// By: padawan
		try {
			SimpleDateFormat formatSend = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String sDateTime = formatSend.format(dateReceived);
			DateHelper dateHelperAccess = new DateHelper();

			String dateWithoutTime = sDateTime.substring(0, 9);

			Date sDate = dateHelperAccess.getStringReturnDateBar(dateWithoutTime);

			return sDate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date ObjDateTimeDB(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "br"));

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getStringReturnHour(String hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "br"));
		try {
			return sdf.parse(hour);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getStringReturnDateBar(String date) {
//		yyyy/MM/dd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("pt", "br"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getStringReturnDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "br"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	 
//	public static Date getStringReturnTimesTamp(String date, String hour) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "br"));
//		try {
//			return sdf.parse(date);
//		} catch (ParseException e) {
//			return null;
//		}
//	}
	

	
	
	public static Date getStringReturnTimesTamp(String dateReceived, String hour) throws ParseException {

		try {
			// junta data com hora e devolve com o formato yyyy/MM/dd HH:mm:ss
			// utilizado para comparação da dataTime do certame
			SimpleDateFormat formatReceived = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatSend = new SimpleDateFormat("yyyy/MM/dd");
			Date date = formatReceived.parse(dateReceived.toString());
			String sDateTime = formatSend.format(date);
			sDateTime = sDateTime + " " + hour + ":00";
			DateHelper dateHelperAccess = new DateHelper();
			Date sDate = dateHelperAccess.getStringReturnDateBar(sDateTime);
			return sDate;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getDateReturnString(Date dtData) {
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date newData = formatBra.parse(dtData.toString());
			return (formatBra.format(newData));

		} catch (ParseException Ex) {
			return "Erro na conversão da data";
		}
	}

	public static String getDataReturnDataFormatUpdateStatus(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static Date ObjDateTime() {
		return objDateTime(dateTime());
	}

	public static Date DateTodayInGregorianDate() throws ParseException {
		Date dateToday = objDateTime(dateTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		String sDate = sdf.format(dateToday);
		Date d = sdf.parse(sDate); 
		return d;
	} 

	public static Date DateUSAtoPTbrConverter(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		String sDate = sdf.format(date);
		Date d = sdf.parse(sDate);
		return d;
	}

	public static Date oneYearAgo(Date Date) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1); // to get previous year add -1
		Date nextYear = cal.getTime();

		return nextYear;

	}

	public static String dateTime(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		return sdf.format(date);
	}

	public static String dateTimeNm() {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String Date(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
		return sdf.format(date);
	}

	public static String dateConverter(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", new Locale("pt", "br"));

		return sdf.format(date);
	}

	public static String dateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		return sdf.format(date);
	}

	public static String dateTime() {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String date() {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String dateYEAR() {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String checkerGP(Date date) {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy", new Locale("pt", "br"));
		return sdf.format(hoje);

	}

	public static String dateMONTH(Date date) {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String dateDAY(Date date) {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM", new Locale("pt", "br"));
		return sdf.format(hoje);
	}

	public static String dateYEAR(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", new Locale("pt", "br"));
		return sdf.format(date);
	}

	/**
	 * Soma uma quantidade de dias a uma data
	 * 
	 * @param dias
	 * @return java.util.Date somada
	 */
	public static Date addDays(int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}

	/**
	 * Soma uma quantidade de segundos a uma data
	 * 
	 * @param dias
	 * @return java.util.Date somada
	 */
	public static Date addSecond(int segundos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, segundos);
		return calendar.getTime();
	}

	/**
	 * Soma uma quantidade de milisegundos a uma data
	 * 
	 * @param dias
	 * @return java.util.Date somada
	 */
	public static Date addMillisecond(int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MILLISECOND, millisecond);
		return calendar.getTime();
	}

	/**
	 * Soma uma quantidade de dias a uma data
	 * 
	 * @param date, dias
	 * @return java.util.Date somada
	 */
	public static Date addDays(Date date, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}

	/**
	 * Soma uma quantidade de meses a uma data
	 * 
	 * @param date, mes
	 * @return java.util.Date somada
	 */
	public static Date addMonths(Date date, int meses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, meses);
		return calendar.getTime();
	}

	// ------------------------------------------------------------------------------------
	// This method replaces various .NET date instance properties, such as 'Hour'.
	// ------------------------------------------------------------------------------------
	public static int datePart(int calendarDatePart, Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		if (calendarDatePart == java.util.Calendar.MONTH)
			// Month in java.util.Calendar is 0-based, so add 1 to simulate .NET:
			return c.get(calendarDatePart) + 1;
		else
			return c.get(calendarDatePart);
	}

	// ------------------------------------------------------------------------------------
	// This method replaces the .NET static date method 'DaysInMonth'. We follow the
	// .NET convention of using 1 to 12 for months, not 0 to 11 as is common in
	// Java.
	// ------------------------------------------------------------------------------------
	public static int daysInMonth(int year, int month) {
		// Month in java.util.Calendar is 0-based, so subtract 1:
		java.util.Calendar cal = new java.util.GregorianCalendar(year, month - 1, 1);
		return cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	}

	// ------------------------------------------------------------------------------------
	// This method replaces the .NET static date property 'Today'.
	// ------------------------------------------------------------------------------------
	public static java.util.Date today() {
		java.util.Calendar now = java.util.Calendar.getInstance();
		// Month in java.util.Calendar is 0-based, so add 1 to simulate .NET:
		return dateForYMDHMS(now.get(java.util.Calendar.YEAR), now.get(java.util.Calendar.MONTH) + 1,
				now.get(java.util.Calendar.DATE), 0, 0, 0);
	}

	// ------------------------------------------------------------------------------------
	// Replaces the deprecated constructor of java.util.Date which takes a year,
	// month,
	// and day, and sets everything else to zero. We follow the .NET convention of
	// using 1 to 12 for months, not 0 to 11 as is common in Java.
	// ------------------------------------------------------------------------------------
	public static java.util.Date dateForYMD(int year, int month, int day) {
		return dateForYMDHMS(year, month, day, 0, 0, 0);
	}

	// ------------------------------------------------------------------------------------
	// Replaces the deprecated constructor of java.util.Date which takes a year,
	// month,
	// day, hour, minute, and second. We follow the .NET convention of using 1 to 12
	// for months, not 0 to 11 as is common in Java.
	// ------------------------------------------------------------------------------------
	public static java.util.Date dateForYMDHMS(int year, int month, int day, int hour, int minute, int second) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.clear();
		// Month in java.util.Calendar is 0-based, so subtract 1:
		cal.set(year, month - 1, day, hour, minute, second);
		return cal.getTime();
	}

	final static String DATE_FORMAT = "dd-MM-yyyy";

//	https://stackoverflow.com/questions/7906301/how-can-i-find-the-number-of-years-between-two-dates
	public static int getCalculaData(Date first, Date last) {
		Calendar caInicial = getCalendar(first);
		Calendar calFinal = getCalendar(last);
		int calculoData = calFinal.get(YEAR) - caInicial.get(YEAR);
		if (caInicial.get(MONTH) > calFinal.get(MONTH)
				|| (caInicial.get(MONTH) == calFinal.get(MONTH) && caInicial.get(DATE) > calFinal.get(DATE))) {
			calculoData--;
		}
		return calculoData;
	}

	public int calculaData(Date first, Date last) throws ParseException {
		long dt = (last.getTime() - first.getTime()) + 3600000;
		int soma = (int) ((dt / 86400000L) / 365);

		if (soma < 1) {
			this.sumDias += dt;
		} else {
			if (dt >= 31536000000L) {
				this.sumDias += (dt - 31536000000L * soma);
			}
		}

		if (this.sumDias >= 31536000000L) {
			int anos = (int) (this.sumDias / 86400000L) / 365;
			soma += anos;
			this.sumDias = (sumDias - 31536000000L) * anos;
		}
		return soma;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
