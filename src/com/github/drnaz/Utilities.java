package com.github.drnaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Jorge Sarmiento (Dr. Naz) on 4/13/2016.
 */
final class Utilities {
    //If you don't want any other classes to inherit from this one, make it FINAL.

    /**
     * Reads a file, extracts a word from it and scrambles its letters.
     * @param pathToFile Path to the file with the words list
     * @return A random word from the File with its words scrambled. An anagram.
     */
    public static String getAnagram(String pathToFile){
        Random r = new Random();
        if(isTextFile(pathToFile)) {
            try {
                //Read file. If file is not found, FileNotFoundException is caught
                //and it tells the user File wasn't found.

                //We declare fileText as a new Scanner which reads a new File
                //  that is whatever you input. Basically it returns everything
                //  starting from the Start of the String (the //A pattern)
                String fileText = new Scanner(new File(pathToFile))
                        .useDelimiter("\\A").next();

                String[] words = fileText.split("\\t|\\s|\\n");
                //  Get file text's words as an array, separated by
                //  \t = tab
                //  \s = space
                //  \n = new line

                System.out.println("Text file has " + words.length + " words");
                // I'd put this inside an enum if i were you.

                int randIndex = r.nextInt(words.length);

                char w[] = words[randIndex].toCharArray();
                //Convert one of those words into a Char Array so we can play with it.

                //We'll have to work with a temporary char to store the letter
                // to be swapped.
                for (int i = 0; i < w.length - 1; i++) {
                    int j = r.nextInt(w.length - 1); // Get random letter's index.

                    char temp = w[i];   //Current letter as temporary char
                    w[i] = w[j];        //Current letter is now swapped
                    w[j] = temp;        //Swapped letter is now temporal
                }

                System.out.println("Original word: " + words[randIndex]);
                return ("Anagram: " + new String(w));

            } catch (FileNotFoundException e) {
                //File was not found. Tell user it wasn't.
                return "File not Found.";
            }
        }
        //File is not a text file.
        return "Not a Text File.";
    }

    /**
     * Checks if a file's extension is .txt
     * @param filename String of file name
     * @return TRUE if file is .txt, FALSE otherwise.
     */
    static boolean isTextFile(String filename) {
        int i = filename.lastIndexOf('.');
        String ext = filename.substring(i + 1);
        return Objects.equals(ext, "txt");
    }
}
