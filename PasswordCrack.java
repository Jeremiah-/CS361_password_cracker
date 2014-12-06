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


		// this loop checks to see if the user account names or user names are used as passwords
		// does not check for 2 mangle versions
		int oldSize = allInfo.size();
		for (int n = 0; n < allInfo.size(); ++n) {

			if (allInfo.size() < oldSize) {
				--n;
			}
			oldSize = allInfo.size();

			ArrayList<String> userInfo = allInfo.get(n);
			String accountName = userInfo.get(0);
			String firstName = userInfo.get(3).split(" ")[0];
			String lastName = userInfo.get(3).split(" ")[1];
			String mangledAccN;
			String mangledFName;
			String mangledLName;

			for (int i = 0; i < 12; ++i) {
				mangledAccN = whichMangle(i, accountName);
				mangledFName = whichMangle(i, firstName);
				mangledLName = whichMangle(i, lastName);

				encryptAndCheck(mangledAccN, allInfo);
				encryptAndCheck(mangledFName, allInfo);
				encryptAndCheck(mangledLName, allInfo);

				for (int k = 1; k < 12; ++k) {
					String twoMangleAcc = whichMangle(k, mangledAccN);
					String twoMangleFN = whichMangle(k, mangledFName);
					String twoMangleLN = whichMangle(k, mangledLName);
					encryptAndCheck(twoMangleAcc, allInfo);
					encryptAndCheck(twoMangleFN, allInfo);
					encryptAndCheck(twoMangleLN, allInfo);
				}

			}

			// these next two loops only prepend to the regular dictWord.
			// prepend a character (e.g. @string)
			for (int c = 33; c < 126; ++c) {
				mangledAccN = prependCharacter(accountName, c);
				mangledFName = prependCharacter(firstName, c);
				mangledLName = prependCharacter(lastName, c);
				encryptAndCheck(mangledAccN, allInfo);
				encryptAndCheck(mangledFName, allInfo);
				encryptAndCheck(mangledLName, allInfo);
			}

			// append a character (e.g. string9)
			for (int c = 33; c < 127; ++c) {
				mangledAccN = appendCharacter(accountName, c);
				mangledFName = appendCharacter(firstName, c);
				mangledLName = appendCharacter(lastName, c);
				encryptAndCheck(mangledAccN, allInfo);
				encryptAndCheck(mangledFName, allInfo);
				encryptAndCheck(mangledLName, allInfo);
			}

		}


		// this loop reads through the dictionary and tries finding 1 and 2 mangles
		// does not look for 2 mangles with prepending/appending characters (this is done in another loop)
		while (dicReader.hasNextLine()) {

			String dicWord = dicReader.nextLine();
			String mangledWord;

			// this checks mangles of two variations without prepending letters
			for (int i = 0; i < 12; ++i) {
				mangledWord = whichMangle(i, dicWord);
				encryptAndCheck(mangledWord, allInfo);

				for (int k = 1; k < 12; ++k) {
					String twoMangle = whichMangle(k, mangledWord);
					encryptAndCheck(twoMangle, allInfo);
				}
			}

			// these next two loops only prepend to the regular dictWord.
			// prepend a character (e.g. @string)
			for (int c = 33; c < 127; ++c) {
				mangledWord = prependCharacter(dicWord, c);
				encryptAndCheck(mangledWord, allInfo);
			}

			// append a character (e.g. string9)
			for (int c = 33; c < 127; ++c) {
				mangledWord = appendCharacter(dicWord, c);
				encryptAndCheck(mangledWord, allInfo);
			}
		}

		dicReader.close();
		try {
			dicReader = new Scanner(dicFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: tried finding dictionary file and it was not found.");
			return;
		}


		// this loop goes throgh all combos with appended and prepended chars
		while (dicReader.hasNext()) {
			String mangledWord;
			String mangledPrepend;
			String mangledAppend;
			String dicWord = dicReader.nextLine();

			// this loop prepends/appends a letter to a mangled word
			for (int i = 0; i < 12; ++i) {
				mangledWord = whichMangle(i, dicWord);
				for (int c = 33; c < 127; ++c) {

					mangledPrepend = prependCharacter(mangledWord, c);
					mangledAppend = appendCharacter(mangledWord, c);
					encryptAndCheck(mangledPrepend, allInfo);
					encryptAndCheck(mangledAppend, allInfo);
				}
			}

			// this loop goes through all the mangles after the original has a letter prepended/appended
			for (int c = 33; c < 127; ++c) {
				mangledPrepend = prependCharacter(dicWord, c);
				mangledAppend = appendCharacter(dicWord, c);

				for (int i = 1; i < 12; ++i) {
					mangledPrepend = whichMangle(i, mangledPrepend);
					mangledAppend = whichMangle(i, mangledAppend);
					encryptAndCheck(mangledPrepend, allInfo);
					encryptAndCheck(mangledAppend, allInfo);
				}
			}

		}

		dicReader.close();

		
	}

	private static String whichMangle(int mangleID, String word) {

		switch (mangleID) {
			case 0:
				break;

			case 1:
				word = reverseString(word);
				break;

			case 2:
				word = duplicate(word);
				break;

			case 3:
				word = deleteFirst(word);
				break;

			case 4:
				word = deleteLast(word);
				break;

			case 5:
				word = reflectWord(word);
				break;

			case 6:
				word = word.toLowerCase();
				break;

			case 7:
				word = word.toUpperCase();
				break;

			case 8:
				word = capitalize(word);
				break;

			case 9:
				word = ncapitalize(word);
				break;

			case 10:
				word = snakeCase(word, true);
				break;

			case 11:
				word = snakeCase(word, false);
				break;
		} // end switch
		return word;
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
		word = word.toLowerCase();
		StringBuilder str = new StringBuilder(word);

		str.setCharAt(0, (char)((int)str.charAt(0) - 32));

		return str.toString();
	}

	private static String ncapitalize(String word) {
		word = word.toUpperCase();
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

	// takes in the word to be mangled and the decimal value, c,
	// of the ASCII character to prepend
	private static String prependCharacter(String word, int c) {
		char character = (char)c;
		StringBuilder mangledWord = new StringBuilder(Character.toString(character));
		mangledWord.append(word);

		return mangledWord.toString();
	}

	// takes in the word to be mangled and the decimal value, c,
	// of the ASCII character to append
	private static String appendCharacter(String word, int c) {
		char character = (char)c;
		StringBuilder mangledWord = new StringBuilder(word);
		mangledWord.append(character);

		return mangledWord.toString();
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