import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;


public class FileParser {

	private LinkedList<String> wordList;
	
	public FileParser(String fileName)
	{
		BufferedReader br = null;
		Scanner scan;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
		}
		scan = new Scanner(br);
		
		wordList = new LinkedList<String>();
		
		while(scan.hasNext())
		{
			wordList.add(scan.nextLine());
		}
		scan.close();
	}
	
	public LinkedList<String> getWordList()
	{
		return wordList;
	}
	
	
	
	
}
