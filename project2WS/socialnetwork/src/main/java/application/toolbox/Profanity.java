package application.toolbox;

import java.util.ArrayList;

public class Profanity {
	public static  ArrayList<String> badWords (){
		
		ArrayList<String> badWord = new ArrayList<String>();
		
		badWord.add("barnacle");
		badWord.add("fluff");
		badWord.add("crapbiscuits");
		badWord.add("balderdash");
		badWord.add("corncuts");
		badWord.add("sonofamonkey");
		badWord.add("dagnabbit");
		badWord.add("pooonastick");
		badWord.add("sufferin’succotash");
		badWord.add("oh,snap");
		badWord.add("cheeseandcrackers");
		badWord.add("frack");
		badWord.add("shitakemushrooms");
		badWord.add("motherofpearl");
		badWord.add("jeepers");
		badWord.add("holyguacamole");
		badWord.add("drat");
		badWord.add("cuss");
		
		return badWord;
	}

	public static boolean doesNotHaveProfanity(String str) {
		ArrayList<String> badWord = badWords();
		
		//1337 5P34K AKA leet speak
		str = str.replaceAll("1","i");
        str = str.replaceAll("!","i");
        str = str.replaceAll("3","e");
        str = str.replaceAll("4","a");
        str = str.replaceAll("@","a");
        str = str.replaceAll("5","s");
        str = str.replaceAll("7","t");
        str = str.replaceAll("0","o");
        str = str.replaceAll("9","g");
        str = str.replaceAll("5","s");
        
        //remove spacing
        str = str.replaceAll(" ", "");
        str = str.replaceAll("/", "");
        str = str.replaceAll("-", "");
        str = str.replaceAll("_", "");
        str = str.replaceAll(".", "");
        str = str.replaceAll(",", "");

		
		
		for(String words : badWord) {
			if(str.toLowerCase().contains(words)) {
				return false;
			}
		}
		
		return true;
	}
	
/// make a good replacement filter
	public static String replaceBadWords(String str) {
		String badWord = "cuss";
		
		return str = str.replaceAll(badWord, "#@$%");
		
	}
	
	public static void main(String[] args) {
		System.out.println(replaceBadWords("cuss"));
	}

}
