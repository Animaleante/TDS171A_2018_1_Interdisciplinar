package com.tds171a.soboru.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Classe Utils
 * @author Diogo
 *
 */
public class Utils {
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	
	public static String toSlug(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}
	
	public static File getImagerDir() {
//		File dest = new File(System.getProperty("jboss.server.data.dir"), "images");
		File dest = new File(System.getProperty("jboss.home.dir"), "images");
		if(!dest.exists()) {
			dest.mkdirs();
		}
		return dest;
	}
	
	public static String formatDouble(Double num) {
		NumberFormat nf = new DecimalFormat("##.##");
		return nf.format(num);
	}
	
	/**
	 * Metodo estatico para converter datas do objeto java.util.Date para String
	 * @param date
	 * @return
	 */
	public static String dateToOracleDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

    public static Date formataData(String dataFormatoString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(dataFormatoString);
    }

    public static String formataData(Date dataFormatoDate) {
        return new SimpleDateFormat("dd/MM/yyyy EEE").format(dataFormatoDate);
    }
}
