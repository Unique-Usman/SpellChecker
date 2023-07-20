import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import java.lang.String;

/**
 * This program checks the spelling of words in a user-provided file against a dictionary
 * and suggests corrections for misspelled words.
 */
public class SpellChecker {
    public static void main(String[] args) {
        try {
            File file = new File("./words.txt");
            HashSet<String> strSet = new HashSet<>();

            File infile = getInputFileNameFromUser();

            Scanner userFile = new Scanner(infile);
            userFile.useDelimiter("[^a-zA-Z]+");
            storeWordsInHashSet(strSet, file);
            while (userFile.hasNext()) {
                String two = userFile.next();
                String two1 = two.toLowerCase();
                if (!strSet.contains(two1)) {
                    System.out.println(two1 + ":" + corrections(two1, strSet));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void storeWordsInHashSet(HashSet<String> strSet, File file) throws FileNotFoundException {
        Scanner filein = new Scanner(file);
        while (filein.hasNext()) {
            String tk = filein.next();
            strSet.add(tk.toLowerCase());
        }
        filein.close(); // Don't forget to close the Scanner when done reading the file
    }

    /**
     * Lets the user select an input file using a standard file
     * selection dialog box. If the user cancels the dialog
     * without selecting a file, the return value is null.
     *
     * @return The selected input file or null if no file was selected.
     */
    static File getInputFileNameFromUser() {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File for Input");
        int option = fileDialog.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileDialog.getSelectedFile();
    }

    static TreeSet corrections(String badWord, HashSet<String> dictionary) {
        TreeSet<String> tree = new TreeSet<String>();

        // Delete any one of the letters from the misspelled word.
        for (int i = 0; i < badWord.length(); i++) {
            String s = badWord.substring(0, i) + badWord.substring(i + 1);
            if (dictionary.contains(s)) {
                tree.add(s);
            }
        }

        // Change any letter in the misspelled word to any other letter.
        for (int i = 0; i < badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String s = badWord.substring(0, i) + ch + badWord.substring(i + 1);
                if (dictionary.contains(s)) {
                    tree.add(s);
                }
            }
        }

        // Insert any letter at any point in the misspelled word.
        for (int i = 0; i < badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String s = badWord.substring(0, i) + ch + badWord.substring(i);
                if (dictionary.contains(s)) {
                    tree.add(s);
                }
            }
        }

        // Swap any two neighboring characters in the misspelled word.
        for (int i = 0; i < badWord.length() - 1; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String s = badWord.substring(0, i) + badWord.substring(i + 1, i + 2) +
                        badWord.substring(i, i + 1) + badWord.substring(i + 2);
                if (dictionary.contains(s)) {
                    tree.add(s);
                }
            }
        }

        // Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary)
        for (int i = 0; i < badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String s1 = badWord.substring(0, i);
                String s2 = badWord.substring(i);
                if (dictionary.contains(s1) && dictionary.contains(s2)) {
                    tree.add(s1);
                    tree.add(s2);
                }
            }
        }
        return tree;
    }
}

