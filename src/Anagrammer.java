import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//import org.apache.commons.collections.map.MultiValueMap;


public class Anagrammer {

	//<alphabetized order, real order>
	private MultiValueMap<String, String> wordMap;

	//charSet is simply a set version of char[] characters
	//contains information about the array of characters
	private List<Character> charSet;

	private final int MINIMUM_WORD_LENGTH = 2;

	public Anagrammer(Collection<String> wordList, char[] characters)
	{
		if(wordList != null)
		{
			createAnagramMap(wordList);
		}
		if(characters != null)
		{
			charSet = new LinkedList<Character>();
			for(int i = 0; i < characters.length; i++)
			{
				charSet.add(characters[i]);
			}
		}
	}

	//iterate through list, place alphabetical order letters in valueMap
	private void createAnagramMap(Collection<String> wordList)
	{
		wordMap = new MultiValueMap<String, String>();
		Iterator<String> wordIterator = wordList.iterator();
		while(wordIterator.hasNext())
		{
			String currWord = wordIterator.next();
			String keyWord = alphabetizedOrderString(currWord);
			wordMap.put(keyWord, currWord);
		}
	}

	//insertion sort implementation of alphbetizing string
	//i.e. hello -> ehllo
	private String alphabetizedOrderString(String word)
	{
		char[] charWord = word.toCharArray();
		for(int i = 0; i < charWord.length - 1; i++)
		{
			for(int j = i+1; j > 0; j--)
			{
				if(charWord[j] < charWord[j-1])
				{
					char tempChar = charWord[j];
					charWord[j] = charWord[j-1];
					charWord[j-1] = tempChar;
				}
				else
					break;
			}
		}
		return new String(charWord);
	}

	public Collection<String> findAnagrams()
	{

		//list that will hold the actual anagrams
		Set<String> setOGrams = new HashSet<String>();

		//holds the powerset of the letters to use
		List<List<Character>> pset = powerList(charSet);
		
		//iterator to scan through the permutations of the letters to use
		Iterator<List<Character>> subsetIterator = pset.iterator();

		while(subsetIterator.hasNext())
		{
			//currPermute is a 1 permutation of the letters to use
			List<Character> currPermute = subsetIterator.next();
			//only add results that are greater than minimum word length.
			//no words like a, b, c
			if(currPermute.size() >= MINIMUM_WORD_LENGTH)
			{
				//build the string.. one flipping char at a time
				Iterator<Character> charIterator = currPermute.iterator();
				StringBuilder sb = new StringBuilder();
				while(charIterator.hasNext())
				{
					sb.append(charIterator.next());
				}
				//after building the string, alphabetize it it, consult the wordMap
				//then add the results of the wordMap to the resultList
				String keyPerm = alphabetizedOrderString(sb.toString());
				if(wordMap.containsKey(keyPerm.toLowerCase()))
				{
					Collection<String> valueCollection = wordMap.getCollection(keyPerm.toLowerCase());
					if(valueCollection.size() > 0)
					{
						//the add all function of listOGrams is being lame
						//just add all the values from the map collection to listOGrams 
						//jeez is that so hard to do commons API?
						Iterator<String> valueCollIt = valueCollection.iterator();
						while(valueCollIt.hasNext())
						{
							setOGrams.add(valueCollIt.next());
						}
					}
				}
			}
		}
		
		return setOGrams;
	}

	private List<List<Character>> powerList(List<Character> originalList) {
		List<List<Character>> Lists = new LinkedList<List<Character>>();
		if (originalList.isEmpty()) {
			Lists.add(new LinkedList<Character>());
			return Lists;
		}
		List<Character> list = new ArrayList<Character>(originalList);
		Character head = list.get(0);
		List<Character> rest = new LinkedList<Character>(list.subList(1, list.size()));
		for (List<Character> List : powerList(rest)) {
			List<Character> newList = new LinkedList<Character>();
			newList.add(head);
			newList.addAll(List);
			Lists.add(newList);
			Lists.add(List);
		}
		return Lists;
	}

}
