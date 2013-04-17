package com.sg.sqldb.utility;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 
 * 
 */
public class Util {

	public static final SimpleDateFormat SDF_M_D_W = new SimpleDateFormat(
			"M/d E");
	public static final SimpleDateFormat SDF_YY_MM_DD_W = new SimpleDateFormat(
			"yy/MM/dd E");
	public static final SimpleDateFormat SDF_YY_MM_DD_W_HH_MM_SS = new SimpleDateFormat(
			"yy/MM/dd E HH:mm:ss");
	public static final SimpleDateFormat SDF_YY_MM_DD_HH_MM_SS = new SimpleDateFormat(
			"yy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat SDF_YY_MM_DD_HH_MM = new SimpleDateFormat(
			"yy/MM/dd HH:mm");
	public static final SimpleDateFormat SDF_YYYY__MM__DD = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat SDF_YYYY__MM__DD__HH__MM__SS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_HH__MM__SS = new SimpleDateFormat(
			"HH:mm:ss");
	public static final SimpleDateFormat SDF_HHMMSS = new SimpleDateFormat(
			"HHmmss");
	public static final SimpleDateFormat SDF_YY_MM_DD = new SimpleDateFormat(
			"yy/MM/dd");
	public static final DecimalFormat MONEY = new DecimalFormat(
			"###,###,###.00");
	public static final DecimalFormat NUMBER_P2 = new DecimalFormat(
			"#########.00");
	public static int ACCURATE_MODE = 1 << 1;
	public static int FUZZY_MODE = 1 << 2;
	public static int CHINESE_MODE = 1 << 3;
	public static int IGNORECASE_MODE = 1 << 4;

	// ��ĸZʹ����������ǩ�������У�����ֵ
	// i, u, v��������ĸ, ����ǰ�����ĸ

	private static char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I',

			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	// ��ʼ��
	private static int[] table = { 45217, 45253, 45761, 46318, 46826, 47010,
			47297, 47614, 47614, 48119, 49062, 49324, 49896, 50371, 50614,
			50622, 50906, 51387, 51446, 52218, 52218, 52218, 52698, 52980,
			53689, 54481, 55289 };

	public static int indexOf(Object[] target, Object element) {
		for (int i = 0; i < target.length; i++) {
			if (org.eclipse.jface.util.Util.equals(target[i], element)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOfIngnoreCase(String[] target, String element) {
		for (int i = 0; i < target.length; i++) {
			if (target[i].equalsIgnoreCase(element)) {
				return i;
			}
		}
		return -1;
	}

	public static String Replace(String strReplaced, String oldStr,
			String newStr) {
		int pos = 0;
		int findPos;
		while ((findPos = strReplaced.indexOf(oldStr, pos)) != -1) {
			strReplaced = strReplaced.substring(0, findPos) + newStr
					+ strReplaced.substring(findPos + oldStr.length());
			findPos += newStr.length();
		}
		return strReplaced;
	}

	public static String getRandomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		Object initLock = new Object();

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static String getString(Object value) {
		if (value == null)
			return "";
		if (value instanceof Date) {
			return SDF_YY_MM_DD_HH_MM.format((Date) value);
		}

		return value.toString();
	}

	public static boolean isMatch(String text, String cellText, int style) {
		if (text == null || cellText == null) {
			return false;
		}
		// ʹ������ƴ��ƥ��
		if ((style & CHINESE_MODE) != 0) {
			String alphaValue = String2Alpha(cellText);
			String alphaCond = String2Alpha(text);
			return alphaValue.toUpperCase().contains(alphaCond.toUpperCase());
		}

		// ʹ��ģ��ƥ��
		if ((style & FUZZY_MODE) != 0) {
			if ((style & IGNORECASE_MODE) != 0) {
				return cellText.toUpperCase().contains(text.toUpperCase());
			} else {
				return cellText.contains(text);
			}
		} else {
			if ((style & IGNORECASE_MODE) != 0) {
				return cellText.equalsIgnoreCase(text);
			} else {
				return cellText.equals(text);
			}
		}
	}

	/**
	 * ����һ���������ֵ��ַ�������һ������ƴ������ĸ���ַ���
	 * 
	 * @param String
	 *            SourceStr ����һ�����ֵ��ַ���
	 */
	public static String String2Alpha(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Alpha(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	/**
	 * ������,�����ַ�,�õ�������ĸ, Ӣ����ĸ���ض�Ӧ����ĸ �����Ǽ��庺�ַ��� '0'
	 * 
	 * @param char ch ����ƴ������ĸ���ַ�
	 */
	public static char Char2Alpha(char ch) {

		if (ch >= 'a' && ch <= 'z')
			// return (char) (ch - 'a' + 'A');
			return ch;
		if (ch >= 'A' && ch <= 'Z')
			return ch;
		if (ch >= '0' && ch <= '9')
			return ch;

		int gb = gbValue(ch);
		if (gb < table[0])
			return '0';

		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}

		if (i >= 26)
			return '0';
		else
			return alphatable[i];
	}

	/**
	 * �ж��ַ��Ƿ���table�����е��ַ���ƥ��
	 * 
	 * @param i
	 *            table�����е�λ��
	 * @param gb
	 *            ���ı���
	 * @return
	 */
	private static boolean match(int i, int gb) {
		if (gb < table[i])
			return false;

		int j = i + 1;

		// ��ĸZʹ����������ǩ
		while (j < 26 && (table[j] == table[i]))
			++j;

		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];

	}

	/**
	 * ȡ�����ֵı���
	 * 
	 * @param char ch ����ƴ������ĸ���ַ�
	 */
	private static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean isNullOrEmptyString(Object value) {
		return value == null || value.equals("");
	}

	public static boolean isNullOrEmptyResult(SQLResult value) {
		return value == null || value.isEmpty();
	}

	public static void main(String[] args) {
		try {
			System.out
					.println(replaceFriendlyTime("2010-11-09,2010-11-08,2010-11-07,"
							+ "2010-11-06,2010-11-05,2010-11-04,2010-11-03,2010-11-02,"
							+ "2010-11-01,2010-10-31,2010-10-30,2010-10-29"));
			System.out
					.println(replaceFriendlyTime("2010-11-09,2010-11-08,2010-11-07,"
							+ "2010-11-06,2010-11-05,2010-11-04,2010-11-03,2010-11-02,"
							+ "2010-11-01,2010-10-31,2010-10-30,2010-10-29"));
			// Calendar cal = Calendar.getInstance();
			// System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ĳһ�죬�õ�����������е����ڣ���String[]�ķ�ʽ����
	 * @param dateInWeek
	 * @param inputFormat
	 * @param outputFormat
	 * @return
	 * @throws ParseException
	 */
	public static String[] getWeekDateString(String dateInWeek, SimpleDateFormat inputFormat,SimpleDateFormat outputFormat) throws ParseException{
		return getWeekDateString(inputFormat.parse(dateInWeek),outputFormat,0);
	}
	
	/**
	 * ����ĳһ�죬�õ�ĳ�����뵱ǰ�������ɸ����ڵ����е����ڣ���String[]�ķ�ʽ����
	 * @param dateInWeek
	 * @param outputFormat
	 * @param offset  
	 * @return
	 */
	public static String[] getWeekDateString(Date dateInWeek, SimpleDateFormat outputFormat,int offset){
		String[] dateString = new String[7];
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateInWeek);
		int i = cal.get(Calendar.DAY_OF_WEEK)-1;	
		cal.add(Calendar.DATE, -i+7*offset);//����
		dateString[0] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//��һ
		dateString[1] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//�ܶ�
		dateString[2] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//����
		dateString[3] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//����
		dateString[4] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//����
		dateString[5] = outputFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 1);//����
		dateString[6] = outputFormat.format(cal.getTime());
		
		return dateString;
	}

	public static String replaceFriendlyTime(String text) throws Exception {
		// Date today = new Date();

		// ȡǰ���������
		Calendar cal = Calendar.getInstance();
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "����");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "����");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "ǰ��");

		// ȡ����
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1; // ��Ϊ���й����һ��Ϊ��һ�����������1

		cal.add(Calendar.DATE, dayOfWeek);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "������");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "������");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "������");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "������");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "������");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "���ܶ�");

		cal.add(Calendar.DATE, -1);
		text = text.replace(Util.SDF_YYYY__MM__DD.format(cal.getTime()), "����һ");

		return text;
	}


	public static boolean contains(String text, String[] input) {
		for (int i = 0; i < input.length; i++) {
			if (text.contains(input[i]))
				return true;
		}
		return false;
	}

	public static boolean endWith(String text, String[] input) {
		for (int i = 0; i < input.length; i++) {
			if (text.endsWith(input[i]))
				return true;
		}
		return false;
	}


	public static String replaceWith(String source, String[] pat) {
		for (int i = 0; i < pat.length; i++) {
			source = source.replace(pat[i], ",");
		}
		return source;
	}
}
