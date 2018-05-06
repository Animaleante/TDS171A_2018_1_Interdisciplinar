package com.tds171a.soboru.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.Normalizer.Form;
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
	
	/**
	 * Metodo estatico que cria uma conexão com o banco
	 * @return a conexão com o banco
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "soboru", "opet");
	}
	
	/**
	 * Metodo estatico que cria uma PreparedStatement depois de criar uma conexao
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static PreparedStatement prepareStatement(String query) throws SQLException, ClassNotFoundException {
		Connection c = createConnection();
		return (c != null ? c.prepareStatement(query) : null);
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
}
