package application.toolbox;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Profanity {
	public static ArrayList<String> badWords() {

		ArrayList<String> badWord = new ArrayList<String>();

		badWord.add("barnacle");
		badWord.add("fluff");
		badWord.add("balderdash");
		badWord.add("frack");
		badWord.add("jeepers");
		badWord.add("drat");
		badWord.add("cuss");
		
		badWord.add("Barnacle");
		badWord.add("Fluff");
		badWord.add("Balderdash");
		badWord.add("Frack");
		badWord.add("Jeepers");
		badWord.add("Drat");
		badWord.add("Cuss");

		return badWord;
	}

	/**
	 * Returns a boolean if there is profanity in the string even if you use leek speak.
	 * @param str
	 * @return
	 * @author Gibbons
	 */
	public static boolean doesNotHaveProfanity(String str) {
		ArrayList<String> badWord = badWords();

		// 1337 5P34K AKA leet speak
		str = str.replaceAll("1", "i");
		str = str.replaceAll("!", "i");
		str = str.replaceAll("3", "e");
		str = str.replaceAll("4", "a");
		str = str.replaceAll("@", "a");
		str = str.replaceAll("5", "s");
		str = str.replaceAll("7", "t");
		str = str.replaceAll("0", "o");
		str = str.replaceAll("9", "g");
		str = str.replaceAll("5", "s");

		// remove spacing
		str = str.replaceAll(" ", "");
		str = str.replaceAll("/", "");
		str = str.replaceAll("-", "");
		str = str.replaceAll("_", "");
		str = str.replaceAll(".", "");
		str = str.replaceAll(",", "");

		for (String words : badWord) {
			if (str.toLowerCase().contains(words)) {
				return false;
			}
		}

		return true;
	}


	/**
	 * returns a censored string replacing all profanity with #@$%
	 * @param str
	 * @return
	 * @author Gibbons
	 */
	public static String replaceBadWords(String str) {
		ArrayList<String> badWord = badWords();

		for (int i = 0; i < badWord.size(); i++) {
			str = str.replace(badWord.get(i), "#@$%");
			System.out.println(str);
		}
		return str;
	}

	
	
	///////////////////////////////////////// better filter but will take work to get it going.
//	public static String replaceBadWords(String str) {
//		ArrayList<String> badWord = badWords();
//		String filtered = str;
//
//		System.out.println(str);
//		System.out.println(filtered);
//
//		// 1337 5P34K AKA leet speak
//		str = str.replaceAll("1", "i");
//		str = str.replaceAll("!", "i");
//		str = str.replaceAll("3", "e");
//		str = str.replaceAll("4", "a");
//		str = str.replaceAll("@", "a");
//		str = str.replaceAll("5", "s");
//		str = str.replaceAll("7", "t");
//		str = str.replaceAll("0", "o");
//		str = str.replaceAll("9", "g");
//		str = str.replaceAll("5", "s");
//		str = str.replaceAll("2", "s");
//
//		System.out.println(str);
//		// remove spacing
////	str = str.replaceAll(" ", "");
////	str = str.replaceAll("/", "");
////	str = str.replaceAll("-", "");
////	str = str.replaceAll("_", "");
////	str = str.replaceAll(".", "");
////	str = str.replaceAll(",", "");
//		str = str.toLowerCase();
//
//		System.out.println(str);
//		for (String words : badWord) {
//			System.out.println(words);
//
//			if (str.contains(words)) {
//				System.out.println("replace");
//				filtered = filtered.replace(words, "#@$%");
//			}
//		}
//		return filtered;
//
//	}

}
