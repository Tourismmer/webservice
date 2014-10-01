package com.tourismmer.app.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tourismmer.app.constants.Constants;
import com.tourismmer.app.constants.Numeros;

public class Util {

	public static final String FORMATO_DATA = "dd/MM/yyyy";
	
	public static final String FORMATO_DATA_JSON = "dd-MM-yyyy";

	public static final String FORMATO_DATA_MYSQL = "yyyy-MM-dd";

	public static Boolean validateEmail(String email) {
		Boolean invalidEmail = Boolean.FALSE;

		if (isEmptyOrNull(email)) {
			invalidEmail = Boolean.TRUE;

		} else {

			Pattern p = Pattern
					.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
			Matcher m = p.matcher(email);

			if (!m.find()) {
				invalidEmail = Boolean.TRUE;
			}

		}

		return invalidEmail;
	}

	public static Boolean validateCPF(String strCpf) {
		int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
		int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
		String strDigitoVerificador, strDigitoResultado;
		Boolean cpfInvalido = Boolean.FALSE;

		if (!strCpf.substring(0, 1).equals("")) {
			strCpf = strCpf.replace('.', ' ');
			strCpf = strCpf.replace('-', ' ');
			strCpf = strCpf.replaceAll(" ", "");
			for (int iCont = 1; iCont < strCpf.length() - 1; iCont++) {
				iDigitoCPF = Integer
						.valueOf(strCpf.substring(iCont - 1, iCont)).intValue();
				iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
				iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
			}
			iRestoDivisao = (iDigito1Aux % 11);
			if (iRestoDivisao < 2) {
				iDigito1 = 0;
			} else {
				iDigito1 = 11 - iRestoDivisao;
			}
			iDigito2Aux += 2 * iDigito1;
			iRestoDivisao = (iDigito2Aux % 11);
			if (iRestoDivisao < 2) {
				iDigito2 = 0;
			} else {
				iDigito2 = 11 - iRestoDivisao;
			}
			strDigitoVerificador = strCpf.substring(strCpf.length() - 2,
					strCpf.length());
			strDigitoResultado = String.valueOf(iDigito1)
					+ String.valueOf(iDigito2);

			if (strDigitoVerificador.equals(strDigitoResultado)) {
				cpfInvalido = Boolean.FALSE;
			} else {
				cpfInvalido = Boolean.TRUE;
			}

		} else {
			cpfInvalido = Boolean.FALSE;
		}

		return cpfInvalido;
	}

	public static Boolean validateCNPJ(String strCNPJ) {
		int iSoma = 0, iDigito;
		char[] chCaracteresCNPJ;
		String strCNPJ_Calculado;

		if (!strCNPJ.substring(0, 1).equals("")) {
			try {
				strCNPJ = strCNPJ.replace('.', ' ');
				strCNPJ = strCNPJ.replace('/', ' ');
				strCNPJ = strCNPJ.replace('-', ' ');
				strCNPJ = strCNPJ.replaceAll(" ", "");
				strCNPJ_Calculado = strCNPJ.substring(0, 12);
				if (strCNPJ.length() != 14)
					return false;
				chCaracteresCNPJ = strCNPJ.toCharArray();
				for (int i = 0; i < 4; i++) {
					if ((chCaracteresCNPJ[i] - 48 >= 0)
							&& (chCaracteresCNPJ[i] - 48 <= 9)) {
						iSoma += (chCaracteresCNPJ[i] - 48) * (6 - (i + 1));
					}
				}
				for (int i = 0; i < 8; i++) {
					if ((chCaracteresCNPJ[i + 4] - 48 >= 0)
							&& (chCaracteresCNPJ[i + 4] - 48 <= 9)) {
						iSoma += (chCaracteresCNPJ[i + 4] - 48)
								* (10 - (i + 1));
					}
				}
				iDigito = 11 - (iSoma % 11);
				strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0"
						: Integer.toString(iDigito);
				/* Segunda parte */
				iSoma = 0;
				for (int i = 0; i < 5; i++) {
					if ((chCaracteresCNPJ[i] - 48 >= 0)
							&& (chCaracteresCNPJ[i] - 48 <= 9)) {
						iSoma += (chCaracteresCNPJ[i] - 48) * (7 - (i + 1));
					}
				}
				for (int i = 0; i < 8; i++) {
					if ((chCaracteresCNPJ[i + 5] - 48 >= 0)
							&& (chCaracteresCNPJ[i + 5] - 48 <= 9)) {
						iSoma += (chCaracteresCNPJ[i + 5] - 48)
								* (10 - (i + 1));
					}
				}
				iDigito = 11 - (iSoma % 11);
				strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0"
						: Integer.toString(iDigito);
				if (strCNPJ.equals(strCNPJ_Calculado)) {
					return Boolean.FALSE;
				} else {
					return Boolean.TRUE;
				}
			} catch (Exception e) {
				return false;
			}
		} else
			return false;
	}

	public static boolean validateParametersRequired(Object[] fields) {
		if (isNotNull(fields) && (fields.length > 0)) {
			for (int i = 0; i < fields.length; i++) {
				if (isEmptyOrNull(fields[i])
						|| fields[i].toString().equals(
								String.valueOf(Numeros.ZERO))
						|| fields[i].toString().equals("0.0")) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public static String validateParametersRequired(Object[] fields, String[] labels) {
		String invalidFields = Constants.EMPYT;
		Boolean isInvalid = Boolean.FALSE;
		if (isNotNull(fields) && (fields.length > 0)) {
			for (int i = 0; i < fields.length; i++) {
				if (isEmptyOrNull(fields[i])
						|| fields[i].toString().equals(
								String.valueOf(Numeros.ZERO))
						|| fields[i].toString().equals("0.0")) {
					invalidFields += labels[i] + ", ";
					isInvalid = Boolean.TRUE;
				}
			}
		}
		if(isInvalid) invalidFields = invalidFields.substring(0,invalidFields.length()-2);
		return invalidFields;
	}

	public static boolean isNotNull(Object object) {
		return object != null;
	}

	public static String removeFormatting(String param) {

		if (isEmptyOrNull(param)) {
			return Constants.EMPYT;
		}
		param = getString(param);

		param = param.replace(" ", "");
		param = param.replace("-", "");
		param = param.replace("/", "");
		param = param.replace(".", "");
		param = param.replace(",", "");
		param = param.replace("(", "");
		param = param.replace(")", "");

		return param;

	}

	public static Date stringToDate(String date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(date);
		} catch (ParseException ex) {
			return null;
		}
	}
	
	public static Date getDateMySql(ResultSet rs, String colum) {
		try {
			return rs.getDate(colum);
		} catch (SQLException e) {
			return null;
		}
	}


	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.format(date);
		} catch (Exception e) {
			return Constants.EMPYT;
		}
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA);
		try {
			return format.format(date);
		} catch (Exception e) {
			return Constants.EMPYT;
		}
	}

	public static String dateToMysql(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA_MYSQL);
		try {
			return format.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isEmptyOrNull(Object object) {
		boolean retorno = false;

		// Verifica se objeto é nulo
		if (isNull(object)) {
			return true;
		}

		// Testa Coleções vazias
		else if (object instanceof Collection) {
			retorno = ((Collection<?>) object).isEmpty();
		}

		// Testa Strings vazias
		else if (object instanceof String) {
			retorno = object.toString().trim().equals(Constants.EMPYT);
		}

		// Testa Vetores vazios
		else if (object instanceof Integer[]) {
			retorno = (((Integer[]) object).length == 0 ? true : false);
		}

		// Testa Vetores vazios
		else if (object instanceof TreeMap<?, ?>) {
			retorno = (((TreeMap<?, ?>) object).size() == 0 ? true : false);
		}

		return retorno;
	}

	public static boolean isNotEmptyOrNull(Object valor) {
		return !isEmptyOrNull(valor);
	}

	public static boolean isNull(Object object) {
		// decisao
		return (object == null);
	}

	public static int getInt(final String valor) {
		try {
			return Integer.parseInt(valor);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getInt(final Long valor) {
		try {
			return valor.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getInt(final Integer valor) {
		try {
			return valor.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getLong(final Integer valor) {
		try {
			return valor.longValue();
		} catch (Exception e) {
			return 0L;
		}
	}

	public static long getLong(final String valor) {
		try {
			return Long.parseLong(valor);
		} catch (Exception e) {
			return 0L;
		}
	}

	public static long getLong(final Long valor) {
		try {
			return valor.longValue();
		} catch (Exception e) {
			return 0L;
		}
	}

	public static double getDouble(final String valor) {
		try {
			return Double.parseDouble(valor);
		} catch (Exception e) {
			return 0D;
		}
	}

	public static double getDouble(final Double valor) {
		try {
			return valor.doubleValue();
		} catch (Exception e) {
			return 0D;
		}
	}

	public static String getString(final String valor) {
		if (valor == null) {
			return "";
		}
		return valor;
	}

	public static String getString(final Object valor) {
		if (valor == null) {
			return "";
		}
		return valor.toString();
	}

	public static String getString(final Double valor) {
		if (valor == null) {
			return "";
		}
		return valor.toString();
	}

	public static String getString(final Integer valor) {
		if (valor == null) {
			return "";
		}
		return String.valueOf(valor);
	}

	public static boolean isNotEmptyOrNullOrZero(Object valor) {
		return !isEmptyOrNullOrZero(valor);
	}

	public static boolean isEmptyOrNullOrZero(Object valor) {
		return isEmptyOrNull(valor)
				|| valor.toString().equals(String.valueOf(Numeros.ZERO))
				|| valor.toString().equals(
						String.valueOf(Numeros.ZERO_PONTO_ZERO));
	}

}
