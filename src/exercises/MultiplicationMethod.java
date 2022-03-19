package exercises;


import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: MultiplicationMethod.java
 *
 * Simulate a 16 bit-computer using the multiplication method to insert the
 * top 50 keywords into a size 64 of the hash table.
 *
 * @author JianBin Wu
 *
 */
public class MultiplicationMethod {
    /** Hash table*/
    private static LinkedList<LinkedList<String>> hashTable = new LinkedList<>();

    /**
     * Initialize hash table with size 64.
     */
    public MultiplicationMethod() {
        for (int i = 0; i < 64; i++) {
            hashTable.add(i, new LinkedList<String>());
        }
    }

    /**
     * Hashing Function using multiplication method.
     *
     * @param s keyword string
     * @return
     */
    public static int multiplicationHashFunction(String s)
    {
        int stringLength = s.length();
        double hashResult = 0;
        double temp = 0;
        //Transform the keyword into ascii code and add them up.
        while(stringLength != 0)
        {
            for (int i = 0; i < s.length(); i++) {
                temp= s.charAt(i);
                hashResult = hashResult + temp;
                stringLength--;
            }

        }

        //multiplication process in bit format.
        double k = hashResult;
        int m = 64;
        int p = 6;
        double w = 16;
        double A = (Math.sqrt(5.0)-1)/2 ;
        double S = A * Math.pow(2.0,w);
        int binaryResult = (int) Math.round(k * S);
        String binary = Integer.toBinaryString(binaryResult);
        //get R0
        int last16Bit = binary.length() - 16;
        String last16Bits = binary.substring(last16Bit, binary.length());
        // first six significant bits
        String significantBits = last16Bits.substring(0,p);
        // convert back to decimal
        int hashResults = Integer.parseInt(significantBits,2);
        return hashResults;

    }

    /**
     * Insert keyword into hash table
     * @param s keyword string
     */
    public static void multiplicationInsert(String s) {
        int haxResult = multiplicationHashFunction(s);
        hashTable.get(haxResult).add(s);
    }

    /**
     * Search a keyword in the hashing table.
     * @param s keyword String
     * @return the slot of the keyword if found else Not Found
     */
    public static String multiplicationSearch(String s) {
        int haxResult = multiplicationHashFunction(s);
        boolean isFound = false;
        for(int i = 0; i < hashTable.get(haxResult).size(); i++){
            if(hashTable.get(haxResult).get(i).equals(s)){
                isFound = true;
            }
        }
        if(isFound){
            return "Slot: " + haxResult;
        }
        else
        {
            return "Not Found";
        }
    }

    /**
     * Delete an existing keyword
     * @param s keyword to delete
     */
    public static void multiplicationDelete(String s) {
        int haxResult = multiplicationHashFunction(s);
        for(int i = 0; i < hashTable.get(haxResult).size(); i++){
            if(hashTable.get(haxResult).get(i).equals(s)){
                hashTable.get(haxResult).remove(i);
            }

        }
    }


    public static void main (String[] args) {

        new MultiplicationMethod();
        Scanner scanner = new Scanner(System.in);

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
            multiplicationInsert(s);
        }
        System.out.println("Here are the top 50 keywords in the hash table");
        for(int i = 0 ; i < hashTable.size(); i++){
            System.out.println(hashTable.get(i));
        }
        System.out.println("---------------------------------");
        System.out.println("1. Insert a keyword");
        System.out.println("2. Search a keyword");
        System.out.println("3. Delete a keyword");
        System.out.println("press any other number to leave");
        int selection = scanner.nextInt();
        while(selection == 1 || selection == 2 || selection == 3)
        {
            if(selection == 1) {
                // allow user to insert key word
                System.out.println("Enter keyword: ");
                String s = scanner.next();
                multiplicationInsert(s);
                System.out.println("Inserted");
                for(int i = 0 ; i < hashTable.size(); i++){
                    System.out.println(hashTable.get(i));
                }
                System.out.println("---------------------------------");
                System.out.println("1. Insert a keyword");
                System.out.println("2. Search a keyword");
                System.out.println("3. Delete a keyword");
                System.out.println("press any other number to leave");
            }
            if(selection == 2){
                System.out.println("Enter keyword: ");
                String s = scanner.next();
                String a = multiplicationSearch(s);
                System.out.println(a);
                System.out.println("---------------------------------");
                for(int i = 0 ; i < hashTable.size(); i++){
                    System.out.println(hashTable.get(i));
                }
                System.out.println("---------------------------------");
                System.out.println("1. Insert a keyword");
                System.out.println("2. Search a keyword");
                System.out.println("3. Delete a keyword");
                System.out.println("press any other number to leave");
            }
            if(selection == 3){
                System.out.println("Enter keyword: ");
                String s = scanner.next();
                multiplicationDelete(s);
                System.out.println("Deleted");
                System.out.println("---------------------------------");
                for(int i = 0 ; i < hashTable.size(); i++){
                    System.out.println(hashTable.get(i));
                }
                System.out.println("---------------------------------");
                System.out.println("1. Insert a keyword");
                System.out.println("2. Search a keyword");
                System.out.println("3. Delete a keyword");
                System.out.println("press any other number to leave");
            }

            selection = scanner.nextInt();
        }
        if( selection != 1 && selection != 2 && selection != 3){
            System.out.println("Thanks for using! Bye! ");
        }

    }
}
