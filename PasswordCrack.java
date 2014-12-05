import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class PasswordCrack {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Error: too few arguments given. " + 
				"Form should be: java PasswordCrack dictionaryFile passwordsToCrackFile");
			return;
		} else if (args.length > 2) {
			System.out.println("Error: too many arguments given. " +
				"Form should be: java PasswordCrack dictionaryFile passwordsToCrackFile");
			return;
		}

		String dicFileName = args[0];
		String pwFileName = args[1];

		File dicFile;
		File pwFile;

		try {
			dicFile = new File(dicFileName);
			pwFile = new File(pwFileName);
		} catch (NullPointerException e) {
			System.out.println("Error: file name given is null.");
			return;
		}

		Scanner dicReader;
		Scanner pwReader;

		try {
			dicReader = new Scanner(dicFile);
			pwReader = new Scanner(pwFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: one of the given files was not found.");
			return;
		}

		ArrayList<String> pwLine = new ArrayList<String>();
		while (pwReader.hasNextLine()) {
			pwLine.add(pwReader.nextLine());
		}

		ArrayList<ArrayList<String>> allInfo = new ArrayList<ArrayList<String>>();

		parseInfo(pwLine, allInfo);
		// just checking to see if the info is parsing correctly
		
		// for (ArrayList<String> info : allInfo) {
		// 	for (String stuff : info) {
		// 		System.out.print(stuff + " ");
		// 	}
		// 	System.out.println();
		// }

		// guess a password, get the salt from the encrypted password,
		// encrypt our guessed password with the salt retrieved,
		// compare the two encrypted passwords. 

		// step 1 says to just encrypt the word from the dictionary

		// step 2, use 1 mangle

		// step 3, use 2 mangles
		


	}

	private static void parseInfo(ArrayList<String> parseMe, ArrayList<ArrayList<String>> allInfo) {

		for (String line: parseMe) {
			ArrayList<String> accountInfo = new ArrayList<String>();
			int colonPosition = line.indexOf(':');

			// this instance, it is the account name
			String temp = line.substring(0, colonPosition);
			accountInfo.add(temp);

			line = line.substring(colonPosition + 1);
			
			// this is the encrypted password
			colonPosition = line.indexOf(':');
			temp = line.substring(0, colonPosition);
			accountInfo.add(temp);

			// skip the two numbers after the 
			line = line.substring(colonPosition + 1);
			colonPosition = line.indexOf(':');
			line = line.substring(colonPosition + 1);
			colonPosition = line.indexOf(':');
			line = line.substring(colonPosition + 1);

			// this is the user's name 
			colonPosition = line.indexOf(':');
			temp = line.substring(0, colonPosition);
			accountInfo.add(temp);

			allInfo.add(accountInfo);

		}
	}


}