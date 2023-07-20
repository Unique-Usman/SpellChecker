# Spell Checker using HashSet and TreeSet - README

## Introduction
This spell checker program utilizes the Java Collection Framework's HashSet and TreeSet classes to implement a simple spell checker. It reads a list of English words from a file and stores them in a set (HashSet) to create a dictionary. The program then checks the spelling of words from another input file against the dictionary. Additionally, the spell checker provides a list of possible correct spellings for misspelled words.

## Set Methods
The spell checker uses several methods defined in the Set interface:
- `set.size()`: Returns the number of items in the set.
- `set.add(item)`: Adds the item to the set if it is not already present.
- `set.contains(item)`: Checks whether the set contains the given item.
- `set.isEmpty()`: Checks whether the set is empty.

## Reading a Dictionary
The spell checker reads the list of English words from the file `words.txt`. It stores the words in a HashSet to efficiently perform word lookups.

## Checking Words in a File
After creating the dictionary set, the spell checker allows the user to select another input file containing words to be checked. The program reads words from this input file and checks if each word is correctly spelled by looking it up in the dictionary. If a word is not found in the dictionary, it is considered misspelled.

## Providing a List of Possible Correct Spellings
In addition to identifying misspelled words, the spell checker suggests possible correct spellings for each misspelled word. The `corrections()` method creates and returns a TreeSet containing variations on the misspelled word that are present in the dictionary. These variations include:
- Deleting any one of the letters from the misspelled word.
- Changing any letter in the misspelled word to any other letter.
- Inserting any letter at any point in the misspelled word.
- Swapping any two neighboring characters in the misspelled word.
- Inserting a space at any point in the misspelled word to produce two words that are both present in the dictionary.

## How to Use the Spell Checker
1. Ensure that the file `words.txt` contains the list of English words to be used as the dictionary.
2. Run the program, and it will read the words from `words.txt` and store them in a HashSet.
3. The program will prompt you to select an input file to check for spelling errors.
4. Once the input file is selected, the spell checker will read its contents, convert each word to lowercase, and check if it is in the dictionary.
5. If a misspelled word is found, the program will display the word and a list of possible correct spellings (if any).

Please note that this is a basic spell checker and may not handle all cases perfectly. However, it serves as a good introduction to using HashSet and TreeSet in the Java Collection Framework for efficient word lookups and suggestions.
