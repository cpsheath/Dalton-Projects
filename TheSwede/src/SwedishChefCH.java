import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class SwedishChefCH {
	static String data = "sample_data.txt";
	
	/*
	 * Implement the rules here
	 */
	public static String translate(String input) throws InvalidSentenceException {		
		
		//1. All sentences end with “BORK BORK BORK!”
		input = input.replaceAll("\\Z", " BORK BORK BORK!");
		
		//2. Replace all occurrences of the word “THE” with “ZEE”.
		input = input.replaceAll("THE", "ZEE");
		
		//3. Replace all occurrences of “A” before “N” with “U”.
		input = input.replaceAll("AN", "UN");
		
		//4. Replace all occurrences of “AU” with “OO”.
		input = input.replaceAll("AU", "OO");
		
		//5. Replace all occurrences of “A” not at the end of a word with “E”.
		input = input.replaceAll("A\\b", "E");
		
		//6. Delete all occurrences of “W” after “O”.
		input = input.replaceAll("OW", "O");
		
		//7. Replace all occurrences of “S” followed by an “H” with “Z”.
		input = input.replaceAll("SH", "ZH");
		
		//8. Replace all occurrences of “I” before “R” with “U”.
		input = input.replaceAll("IR", "UR");
		
		//9. Replace all occurrences of the word fragment “TION” with “SHUN”.
		input = input.replaceAll("TION", "SHUN");
		
		//10. Replace the first occurrence of “I” in a word other than at the start of the word with “EE”.
		input = input.replaceAll("\\b([A-HJ-Z]+)I", "EE");
		
		
		//11. Replace all occurrences of “N” preceded by “E” at the end of a word with “E”.
		input = input.replaceAll("EN\\b", "E");
		
		//12. Duplicate any single occurrence of “F” and “L”.
		input = input.replaceAll("F", "FF");
		input = input.replaceAll("L", "LL");
		
		//13. Append “­A” to any word that ends with a vowel.
		input = input.replaceAll("A\\b", "A-A");
		input = input.replaceAll("E\\b", "E-A"); //come back to this
		input = input.replaceAll("I\\b", "I-A");
		input = input.replaceAll("O\\b", "O-A");
		input = input.replaceAll("U\\b", "U-A");
		
		//14. Replace all occurrences of “U” except at the start of a word with “OO”.
		input = input.replaceAll("\\bU", "OO");
		
		//15. Replace all occurrences of “V” with “FLURG”.
		input = input.replaceAll("V", "FLURG");

		//16. Replace last occurrence of “Y” not followed by a vowel with “EE”
		input = input.replaceAll("", ""); //not done
		
		//17. Replace one or more occurrence of “S” not at the end of a word with an “X”
//		input = input.replaceAll("S*\\b", "X");
		
		//18. Replace “TH” followed by a vowel with “D”
		input = input.replaceAll("THA", "D");
		input = input.replaceAll("THE", "D");
		input = input.replaceAll("THI", "D");
		input = input.replaceAll("THO", "D");
		input = input.replaceAll("THU", "D");
		
		//19. Duplicate any word fragment that is a consonant followed by an “O”.
		input = input.replaceAll("([BCDFGHJKLMNPQRSTVWXYZ]O)", "");
		
		//20. Swap the letters before and after any hyphen.
		
		
		return input;
	}

	public static void main(String[] args) throws InvalidSentenceException, IOException {
		//declarations:
		int score=0;
		int total=0;
		Scanner scan;
		String refbase = "http://compsci.dalton.org:8080/chef/chef.jsp?t=";
		
		//open the data file:
		scan = new Scanner(new BufferedReader(new FileReader(data)));
		
		//read all lines from file:
		while(scan.hasNext()) {
			String input, myoutput, refoutput;
			
			//get input:
			input = scan.nextLine().trim();
			
			//get my answer:
			try {
				myoutput = translate(input);
			} catch (InvalidSentenceException e) {
				e.printStackTrace();
				continue;
			}

			//get correct answer:
			String param = URLEncoder.encode(input, "UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader((new URL(refbase+param)).openStream()));
			in.readLine(); //ditch empty lines
			refoutput = in.readLine();
			in.close();
			
			//check results, print if wrong
			if(refoutput.equals(myoutput)) {
				score++;
			} else {
				System.out.println("MY:  " + myoutput);
				System.out.println("REF: " + refoutput);
			}
			
			//count total lines
			total++;
		}
		
		//give results:
		System.out.println("SCORE: " + score + " out of " + total);

		//close the file
		scan.close();
	}
	
}

@SuppressWarnings("serial")
class InvalidSentenceException extends Exception {
	public InvalidSentenceException(String reason) {
		this.initCause(new Throwable(reason));
	}
}