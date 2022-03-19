package exercises;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: DoubleHashing.java
 *
 * Implementation of Double Hashing method to insert the top 50
 * keywords into a size of 57 hash table.
 *
 * @author JianBin Wu
 *
 */
public class DoubleHashing {
    /** Hash table*/
    private static LinkedList<String> hashTable = new LinkedList<>();

    /**
     * initialize hash table with size 57.
     */
    public DoubleHashing() {
        for (int i = 0; i < 57; i++) {
            hashTable.add("null");
        }
    }

    /**
     * First Hashing Function.
     *
     * @param s keyword String
     * @return the hashing result
     */
    public static int firstHashingFunction(String s){
        int stringLength = s.length();
        double hashResult = 0;
        double temp = 0;
        //Transform the keyword into ascii code, base 26 and add them up.
        while(stringLength != 0) {
            for (int i = 0; i < s.length(); i++) {
                temp= s.charAt(i);
                temp = temp * Math.pow(26, i);
                hashResult = hashResult + temp;
                stringLength--;
            }

        }
        // first hashing function K mod 57
        hashResult = hashResult % 57;
        int hashResults = (int) Math.round(hashResult);
        return hashResults;

    }

    /**
     * Second hashing function.
     *
     * @param s keyword String
     * @return the hashing result
     */
    public static int secondHashingFunction(String s){
        int stringLength = s.length();
        double hashResult = 0;
        double temp = 0;
        //Transform the keyword into ascii code, base 26
        while(stringLength != 0) {
            for (int i = 0; i < s.length(); i++) {
                temp= s.charAt(i);
                temp = temp * Math.pow(26, i);
                hashResult = hashResult + temp;
                stringLength--;
            }

        }

        // second hashing function 1 + (k mod 13)
        hashResult = 1 + (hashResult % 13);
        int hashResults = (int) Math.round(hashResult);
        return hashResults;
    }

    /**
     * Insert keyword into hash table
     *
     * @param s keyword string
     */
    public static void doubleHashingInsert(String s) {
        //first hashing function result
        int firstHashResult = firstHashingFunction(s);
        int secondHashResult;
        int hashResult = firstHashResult;
        int probe = 0;
        if (hashTable.get(firstHashResult).equals("null")) {
            hashTable.set(firstHashResult, s);
        } else {
            while (!hashTable.get(hashResult).equals("null")) {
                probe++;
                //second hashing function result
                secondHashResult = secondHashingFunction(s);

                //double hashing h(k,i) = ((h1(k) + i h2(k)) mod m
                hashResult = (firstHashResult + (probe * secondHashResult)) % 57;
            }
            hashTable.set(hashResult, s);
        }
    }

    /**
     * Search a keyword in the hashing table.
     * @param s keyword String
     * @return the slot of the keyword if found else Not Found
     */
    public static String doubleHashingSearch(String s){
        int slot = 0;
        boolean isFound = false;
        for(int i = 0; i < hashTable.size(); i++){
            if(hashTable.get(i).equals(s)){
                slot = i ;
                isFound = true;
            }

        }
        if (isFound){
            return "Slot: " + slot;
        }
        else {
            return "Not Found";
        }

    }



    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        new DoubleHashing();

        String[] keyWords = {"facebook","youtube","gmail","google","weather",
                "ebay","yahoo","walmart","yahoo mail","google translate","google maps",
                "craigslist","netflix","google docs", "translate","home depot","news","fox news",
                "calculator","usps tracking","cnn","hotmail","google drive","maps","paypal",
                "lowes","instagram","msn","amazon prime","target","espn","zillow","bank of america",
                "wells fargo","twitter","google classroom","indeed","best buy","speed test","linkedin",
                "aol mail","hulu","you tube","pinterest","trump","nba","roblox","capital one","traductor"
        };
        //insert keywords to hash table
        for(String s : keyWords)
        {
            doubleHashingInsert(s);
        }
        System.out.println("Here are the top 50 keywords in the hash table");
        for(int i = 0 ; i < hashTable.size(); i++){
            System.out.println(hashTable.get(i));
        }
        System.out.println("1. Insert a keyword");
        System.out.println("2. Search a keyword");
        System.out.println("press any other number to leave");
        int selection = scanner.nextInt();
        while(selection == 1 || selection == 2)
        {
            if(selection == 1)
            {
                System.out.println("Enter keyword: ");
                String s = scanner.next();
                doubleHashingInsert(s);
                System.out.println("Inserted");
                // allow user to insert key word
                for(int i = 0 ; i < hashTable.size(); i++){
                    System.out.println(hashTable.get(i));
                }
                System.out.println("---------------------------------");
                System.out.println("1. Insert a keyword");
                System.out.println("2. Search a keyword");
                System.out.println("press any other number to leave");
            }

            if(selection == 2){
                System.out.println("Enter keyword: ");
                String s = scanner.next();
                String a = doubleHashingSearch(s);
                System.out.println(a);
                System.out.println("---------------------------------");
                for(int i = 0 ; i < hashTable.size(); i++){
                    System.out.println(hashTable.get(i));
                }
                System.out.println("---------------------------------");
                System.out.println("1. Insert a keyword");
                System.out.println("2. Search a keyword");
                System.out.println("press any other number to leave");
            }
            selection = scanner.nextInt();
        }
        if( selection != 1 && selection != 2)
        {
            System.out.println("Thanks for using! Bye! ");
        }
    }
}
