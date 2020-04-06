package br.desafio.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.Random;

import javax.swing.text.MaskFormatter;

public final class StringHelper {

	public StringHelper() {
		super();
	}

	public static boolean isNullOrEmpty(String string) {
		return stringsEqual(string, "null") || string == null || string.equals("");
	}

	public static boolean isEmpty(String string) {
		return stringsEqual(string, "");
	}

	public static String join(String separator, String[] stringarray) {
		if (stringarray == null)
			return null;
		else
			return join(separator, stringarray, 0, stringarray.length);
	}

	public static String join(String separator, String[] stringarray, int startindex, int count) {
		String result = "";

		if (stringarray == null)
			return null;

		for (int index = startindex; index < stringarray.length && index - startindex < count; index++) {
			if (separator != null && index > startindex)
				result += separator;

			if (stringarray[index] != null)
				result += stringarray[index];
		}

		return result;
	}

	public static String trimEnd(String string, Character... charsToTrim) {
		if (string == null || charsToTrim == null)
			return string;

		int lengthToKeep = string.length();
		for (int index = string.length() - 1; index >= 0; index--) {
			boolean removeChar = false;
			if (charsToTrim.length == 0) {
				if (Character.isWhitespace(string.charAt(index))) {
					lengthToKeep = index;
					removeChar = true;
				}
			} else {
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++) {
					if (string.charAt(index) == charsToTrim[trimCharIndex]) {
						lengthToKeep = index;
						removeChar = true;
						break;
					}
				}
			}
			if (!removeChar)
				break;
		}
		return string.substring(0, lengthToKeep);
	}

	// planilha.xls
	public static String readFile(byte[] filename) {
		StringBuilder records = new StringBuilder();
		try {

			ByteArrayInputStream is = new ByteArrayInputStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				records.append(line + ",");

			}
			reader.close();
			return records.toString();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}
  
	public boolean checkerInteiro(String string) {
		// cria um array de char
		String stringTrim = string.trim();
		if(!StringHelper.isNullOrEmpty(stringTrim)) {
			char[] c = stringTrim.toCharArray();
			boolean d = true;
			for (int i = 0; i < c.length; i++)
				// verifica se o char não é um dígito
				if (!Character.isDigit(c[i])) {
					d = false;
					break;
				}
			return d;
		}
		return false;
	}

	public static String trimStart(String string, Character... charsToTrim) {
		if (string == null || charsToTrim == null)
			return string;

		int startingIndex = 0;
		for (int index = 0; index < string.length(); index++) {
			boolean removeChar = false;
			if (charsToTrim.length == 0) {
				if (Character.isWhitespace(string.charAt(index))) {
					startingIndex = index + 1;
					removeChar = true;
				}
			} else {
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++) {
					if (string.charAt(index) == charsToTrim[trimCharIndex]) {
						startingIndex = index + 1;
						removeChar = true;
						break;
					}
				}
			}
			if (!removeChar)
				break;
		}
		return string.substring(startingIndex);
	}

	public static String trimStart(String string, String charsToTrim) {
		return string.replaceAll("^[" + charsToTrim + "]+", "");
	}

	// ------------------------------------------------------------------------------------
	public static String trim(String string, Character... charsToTrim) {
		return trimEnd(trimStart(string, charsToTrim), charsToTrim);
	}

	// ------------------------------------------------------------------------------------
	// This method is used for string equality comparisons when the option
	// 'Use helper 'stringsEqual' method to handle null strings' is selected
	// (The Java String 'equals' method can't be called on a null instance).
	// ------------------------------------------------------------------------------------
	public static boolean stringsEqual(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		else
			return s1 != null && s1.equals(s2);
	}

	public static int stringsCompare(String s1, String s2) {
		try {
			if (s1 == null && s2 == null)
				return 0;
			else if(s1 != null)
				return s1.compareTo(s2);
			else
				return 0;
		} catch (Exception ex) {
			return 0;
		}
	}

	public static boolean stringsEqualIgnoreCase(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		else
			return s1 != null && s1.equalsIgnoreCase(s2);
	}

	// format("###.###.###-##", CPF);
	public static String format(String pattern, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getValue(String value) {
		if (isNullOrEmpty(value)) {
			return "";
		} else {
			return value;
		}
	}

	public static String getRandomString(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		while (sb.length() < length) {
			int index = (int) (rnd.nextFloat() * chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}
	
    public String formatInt(String inteiro) {
    	inteiro = inteiro.replaceAll("[^0-9]", "");
    	inteiro = inteiro.trim();
    	inteiro = inteiro.replace(".", "").replace("-", "");
    	    	
        return inteiro;
    }
}