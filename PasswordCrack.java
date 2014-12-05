import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Iterator;

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


		// this is where the magic happens ;) ;)
		while (dicReader.hasNextLine()) {

			// TODO: for method that remove characters, we need to make sure the size is not too small
			String dicWord = dicReader.nextLine();
			encryptAndCheck(dicWord, allInfo);

			// reverse the string
			String mangledWord = reverseString(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// checking for a double
			mangledWord = duplicate(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// deleting the first letter
			mangledWord = deleteFirst(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// deleting the last letter
			mangledWord = deleteLast(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// reflect the string (e.g. stringgnirts)
			mangledWord = reflectWord(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// all to lower case
			mangledWord = dicWord.toLowerCase();
			encryptAndCheck(mangledWord, allInfo);

			// all to upper case
			mangledWord = dicWord.toUpperCase();
			encryptAndCheck(mangledWord, allInfo);

			// capitalize the string (e.g. String)
			mangledWord = capitalize(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// ncapitalize the string (e.g. sTRING)
			mangledWord = ncapitalize(dicWord);
			encryptAndCheck(mangledWord, allInfo);

			// even indexes upper case
			mangledWord = snakeCase(dicWord, true);
			encryptAndCheck(mangledWord, allInfo);

			// odd indexes upper case
			mangledWord = snakeCase(dicWord, false);
			encryptAndCheck(mangledWord, allInfo);



			// Remember, in all these methods, we only have to care about the first 8 chars
			// Make seperate methods for the different mangles?
			// another that tries all capitalization combos
			// appending a character to the beginning or the end
			// basically what he brought up in assignment page lol
		}
		
	}

	private static String reflectWord(String word) {
		StringBuilder manWord = new StringBuilder(word);
		String reversedWord = reverseString(word);
		return word + reversedWord;

	}

	private static String duplicate(String word) {
		return word + word;
	}

	private static String deleteFirst(String word) {
		if (word.length() > 1) {
				word = word.substring(1, word.length());
		}

		return word;
	}

	private static String deleteLast(String word) {
		if (word.length() > 1) {
			word = word.substring(0, word.length() - 1);
		}

		return word;
	}

	private static String reverseString(String word) {
		return new StringBuilder(word).reverse().toString();
	}

	private static String capitalize(String word) {
		word.toLowerCase();
		StringBuilder str = new StringBuilder(word);

		str.setCharAt(0, (char)((int)str.charAt(0) - 32));

		return str.toString();
	}

	private static String ncapitalize(String word) {
		word.toUpperCase();
		StringBuilder str = new StringBuilder(word);

		str.setCharAt(0, (char)((int)str.charAt(0) + 32));

		return str.toString();
	}

	private static String snakeCase(String word, boolean firstToUpper) {
		StringBuilder wrd = new StringBuilder(word);
		int mode = 0;
		mode = firstToUpper ? 0 : 1;
		for (int i = mode; i < wrd.length(); i += 2) {
			if (!((int)wrd.charAt(i) < 91)) {
				wrd.setCharAt(i, (char)((int)wrd.charAt(i) - 32));
			}
		}

		return wrd.toString();
	}

	private static void encryptAndCheck(String word, ArrayList<ArrayList<String>> allInfo) {
		// Jcrypt jcrypt = new Jcrypt();
		for (Iterator<ArrayList<String>> it = allInfo.iterator(); it.hasNext();) {
			ArrayList<String> info = it.next();
			String encryptedPW = Jcrypt.crypt(info.get(1), word);
			if (encryptedPW.equals(info.get(2))) {
				System.out.println("Password cracked! Account name: " + info.get(0) +
					"\tEncrypted password: " + info.get(2) + "\tPlaintext password: "
					+ word);

				it.remove();
			}
		}

	}

	private static void parseInfo(ArrayList<String> parseMe, ArrayList<ArrayList<String>> allInfo) {

		for (String line: parseMe) {
			ArrayList<String> accountInfo = new ArrayList<String>();
			int colonPosition = line.indexOf(':');

			// this instance, it is the account name
			String temp = line.substring(0, colonPosition);
			accountInfo.add(temp);

			line = line.substring(colonPosition + 1);
			
			// this is the salt
			colonPosition = line.indexOf(':');
			temp = line.substring(0, 2);
			accountInfo.add(temp);

			// this is the whole password
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