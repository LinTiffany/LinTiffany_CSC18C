/*
 * Author: Tiffany Lin
 * Date: 04/22/15
 * Class:CSC 18C
 * Description: Create and test hash,binary,and search
 */
import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class MaxArray {
	final static int SIZE=2000000;	
	final static int STRING_SIZE = 20;
	
	final static int DEEP = 10;	// the max depth of hash table.
	// each hashkey stores MAX 10 values in its link-list.
	final static int HASH_TABLE_SIZE = SIZE/DEEP;

	static Scanner in=new Scanner(System.in);
	
	static String[] LinearArray=new String[SIZE];
	static BinaryNode BinaryTree=null;
	static HashNode[] HashArray=new HashNode[HASH_TABLE_SIZE];

	/**
	 * Convert string to ASCII value
	 * @return
	 */
	public static boolean hashSearch(String value) {
		Integer hashKey = Math.abs(value.hashCode()) % HASH_TABLE_SIZE;

		if ( HashArray[hashKey] == null ) {
			return false;
		}

		HashNode index = HashArray[hashKey];	// we found the cell with hashKey, 
		// now we search the linked list from the beginning of this cell
		boolean found = false;
		while ( !found && index !=null ) {
			if (index.data.equals(value)) {	
				found = true;
			}
			else {
				// continue search the linked list
				index = index.next;
			}
		}
		return found;
	}

	public static boolean linearSearch(String value){
		for(int i=0;i<LinearArray.length;i++){
			if(LinearArray[i] != null && value.equals(LinearArray[i])){
				return true;
			}
		}
		return false;
	}
	
	public static boolean binarySearch(String value){
		if ( BinaryTree == null ) {
			return false;
		}
		else {
			return BinaryTree.search(value);
		}
	}

	/**
	 * Build up Linear Array
	 */
	public static void addToLinearArray(int i, String s) {
		LinearArray[i] = s;
	}

	/**
	 * Build up Binary Array
	 */
	public static void addToBinaryArray(String value) {
		if ( BinaryTree == null) {
			BinaryTree = new BinaryNode(value);
		}
		else {
			BinaryTree.add(value);
		}
	}


	/**
	 * Build up HashTable
	 */
	public static void addToHashTable(String value) {
		Integer hashKey = Math.abs(value.hashCode()) % HASH_TABLE_SIZE;

		if ( HashArray[hashKey] == null ) { // Brand new hash key, so we set it up, but indicated not found.
			HashNode newNode = new HashNode(value);
			HashArray[hashKey] = newNode;
		}
		else {
			HashNode index = HashArray[hashKey];	// we found the cell with hashKey, 
			// now we search the linked list from the beginning of this cell
			boolean found = false;
			while ( !found && index !=null ) {
				if (index.data.equals(value)) {	// String comparison.
					found = true;
				}
				else {
					// continue in linked list
					index = index.next;
				}
			}

			if (!found) {	// if not found, add Cell to the beginning of link list.
				HashNode newCell = new HashNode(value);
				newCell.next = HashArray[hashKey];
				HashArray[hashKey] = newCell;
			}
		}
	}

	public static void main(String[] args) throws IOException{

		Random rand=new Random();
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		
		for(int j=0;j<SIZE;j++ ){

			String newString="";int i=0;
			while(i< STRING_SIZE){		//create an string
				newString=newString+alphabet.charAt(rand.nextInt(alphabet.length()));
				i++;
			}
			newString = newString+ "\r\n"; // to indicate the end of string.
			//create separate lists for each search
			addToLinearArray(j, newString);
			addToBinaryArray(newString);
			addToHashTable(newString);
		}
				int randomPick=rand.nextInt(SIZE);
				// Because linear, Binary, Hash used the same set of Strings. 
				// So we can use an String in LinearArray to test all searches.
				//randomWord from list LinearArray  [test1]
				String randomWord=LinearArray[randomPick];
				System.out.println();
				System.out.println("Random Word picked: "+randomWord);
				System.out.println("Size of list: "+SIZE);
				//randomWord not included in list LinearArray  [test2]
				//choose random word for search to find:  randomWord="abcdefghijklmnopqrst";
				/**
				 * Test Linear
				 */
				double startTime1=System.nanoTime();
				if(linearSearch(randomWord)==true){
					System.out.println("FOUND in linear search");
				}else{
					System.out.println("NOT FOUND in linear search");
				}
				double endTime1=System.nanoTime();
				System.out.println("Linear Search took: "+(endTime1-startTime1)/1000.0f + " micro-seconds");

				/**
				 * Test Binary
				 */
				double startTime2=System.nanoTime();
				if(binarySearch(randomWord)==true){
					System.out.println("FOUND in binary search");
				}else{
					System.out.println("NOT FOUND in binary search");
				}
				double endTime2=System.nanoTime();
				System.out.println("Binary Search took: "+(endTime2-startTime2)/1000.0f + " micro-seconds");


				/**
				 * TestHash
				 */
				double startTime3=System.nanoTime();
				System.nanoTime();
				if(hashSearch(randomWord)==true){
					System.out.println("FOUND in hash search");
				}else{
					System.out.println("NOT FOUND in hash search");
				}
				double endTime3=System.nanoTime();
				System.out.println("Hash search took: "+(endTime3-startTime3)/1000.0f + " micro-seconds");
				System.out.println();
			
		

		System.out.println("\nEnd of program.");
	}

}
