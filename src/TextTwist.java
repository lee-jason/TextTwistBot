import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class TextTwist {

	public static void main(String[] args) {
		FileParser fp = new FileParser("words.txt");
		System.out.println("done reading in words");
		LinkedList<String> wordList = fp.getWordList();
		Snapshot s = new Snapshot();
		char[] characters = s.findLetters();
		//char[] characters = new char[] { 'a', 'c', 'e', 'f', 'g', 'h' };
		System.out.println(characters);

		Anagrammer anagramm = new Anagrammer(wordList, characters);
		Collection<String> collection = anagramm.findAnagrams();

		InputAnswers ia = new InputAnswers();
		ia.typeInAnswers(collection);
	}

}
