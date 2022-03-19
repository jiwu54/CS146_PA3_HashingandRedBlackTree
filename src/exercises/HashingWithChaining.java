package exercises;

import java.util.LinkedList;
import java.util.Scanner;


/**
 * FileName: HashingWithChaining.java
 *
 * Implementation of Hashing with Chaining method using linked list.
 * Using Division method to insert the top 50 keywords into a size 17 of the hash table.
 *
 * @author JianBin Wu
 *
 */
public class HashingWithChaining{
	/** Hash table*/
	private static LinkedList<LinkedList<String>> hashTable = new LinkedList<>();

	/**
	 * Initialize hash table with size 17.
	 */
	public HashingWithChaining() {
		for (int i = 0; i < 17; i++)
		{
			hashTable.add(i, new LinkedList<String>());
		}
	}

	/**
	 * Hashing Function using division method.
	 *
	 * @param s keyword string
	 * @return hashing result
	 */
	public static int chainedHashFunction(String s) {
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
		//Hashing function h(k) = k mod m
		hashResult = hashResult % 17;
		int hashResults = (int) Math.round(hashResult);
		return hashResults;
		
	}

	/**
	 * Insert keyword into hash table
	 *
	 * @param s keyword string
	 */
	public static void chainedHashInsert(String s) {
		int hashResult = chainedHashFunction(s);
		hashTable.get(hashResult).add(s);
	}

	/**
	 * Search a keyword in the hashing table.
	 *
	 * @param s keyword String
	 * @return the slot of the keyword if found else Not Found
	 */
	public static String chainedHashSearch(String s)
	{
		int hashResult = chainedHashFunction(s);
		boolean isFound = false;
		for(int i = 0; i < hashTable.get(hashResult).size(); i++){
			if(hashTable.get(hashResult).get(i).equals(s)){
				isFound = true;
			}

		}
		if(isFound){
			return "Slot: " + hashResult;
		}
		else
		{
			return "Not Found";
		}

	}

	/**
	 * Delete an existing keyword
	 *
	 * @param s keyword to delete
	 */
	public static void chainedHashDelete(String s) {
		int haxResult = chainedHashFunction(s);
		for(int i = 0; i < hashTable.get(haxResult).size(); i++){
			if(hashTable.get(haxResult).get(i).equals(s)){
				hashTable.get(haxResult).remove(i);
			}

		}
	}
	
	public static void main (String args[]) {

		new HashingWithChaining();
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
			chainedHashInsert(s);
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
		while(selection == 1 || selection == 2 || selection == 3){
			if(selection == 1) {
				System.out.println("Enter keyword: ");
				String s = scanner.next();
				chainedHashInsert(s);
				System.out.println("Inserted");
				for(int i = 0 ; i < hashTable.size(); i++)
				{
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
				String a = chainedHashSearch(s);
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
				chainedHashDelete(s);
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
		if( selection != 1 && selection != 2 && selection != 3)
		{
			System.out.println("Thanks for using! Bye! ");
		}
	}

}

