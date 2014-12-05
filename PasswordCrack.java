import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

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

		// guess a password, get the salt from the encrypted password,
		// encrypt our guessed password with the salt retrieved,
		// compare the two encrypted passwords. 


	}
}