import java.util.*;
import java.util.ListIterator;
public class Dictionary implements DictionaryADT {

	
	private LinkedList<Data>[] hashTable;
	private int size;
	
	public Dictionary(int size) {
		//instantiates the instance variable for dictionary size
		this.size = size;
		//creates an array, with each element of the array containing a linked list
		LinkedList<Data>[] myHashTable = new LinkedList[size];
		
		//instantiates the instance variable
		this.hashTable = myHashTable;

	}
	
	public int put(Data Record) throws DuplicatedKeyException{
		
		//stores the position by running the hash function at the record given
		int hashPos = hashFunc(Record.getKey());
		
		int collisiondetected = 0;
		
		/*loops through the entire table to check if this record already exists. if it does, exception is thrown
		 * starts by having the outside loop cycle through the array containing linked lists. if the array contains a linked list, it will proceed into it.
		 * while the linked list selected at that element contains a piece of data, it will proceed to go in and check if it is duplicate.
		 * if it is not a duplicate, it will proceed to the next element in the linked list and cycle through, until the linked list points
		 * at a null (meaning it has reached the end of the linked list) and then the while loop breaks.
		 */
		
		
		for (int i = 0; i < size; i++)
		{
			if (hashTable[i] != null)
			{
				for(int j = 0; j < hashTable[i].size(); j++)
				{
					if (hashTable[i].get(j).getKey() == Record.getKey())
					{
						throw new DuplicatedKeyException("Key");
					}
				}
					
			}
		}
		
		
		//checks if there was a collision, by checking if a linked list already exists there.
		//if there is an existing linked list, there will be a collision
		if (hashTable[hashPos] != null)
		{
			collisiondetected = 1;
		}
		
		//if there is no linked list, it will make a new one to put into the hash table
		if (hashTable[hashPos] == null)
		{
			hashTable[hashPos] = new LinkedList<Data>();
			//this will add a record to the linked list
			hashTable[hashPos].add(Record);
		}
		//otherwise it will just add the record to whatever linked list is already there
		else {
			hashTable[hashPos].add(Record);
		}
		
		

		
		return collisiondetected;
	}
	
	public void remove(String key) throws InexistentKeyException{
		
		//stores the position by running the hash function at the record given
		int hashPos = hashFunc(key);
		boolean containsKey = false;
		

		//identifies the key element that needs to be removed, by using the get function to check if it is in the dictionary
		Data removeElement = get(key);
		
		//if it was found in the dictionary, it will go into the hash table at a certain linked list identified by the hash function
		if (removeElement != null)
		{
			//this removes the element from the linkedlist
			hashTable[hashPos].remove(removeElement);
			//boolean for if the list found the key
			containsKey = true;
		}
		
		//throws exception if the key was not found
		if (containsKey == false) {
			throw new InexistentKeyException("key");
		}
	}
	
	public Data get(String key) {
		
		//initialize new data object
		Data currentRecord = null;
		//stores the position by running the hash function at the record given
		int hashPos = hashFunc(key);
		
		//if there is no linked list, it will return a null
		if (hashTable[hashPos] == null)
		{
			return null;
		}
		
		//if the record is found, it will retrieve it from the appropriate linked list and return it
		currentRecord = hashTable[hashPos].element();
		
		return currentRecord;

	}
	
	public int numDataItems() {
		
		int counter = 0;

		
		/*loops through the entire table to count the items.
		 * starts by having the outside loop cycle through the array containing linked lists. if the array contains a linked list, it will proceed into it.
		 * while the linked list selected at that element contains a piece of data, it will increment the counter and proceed.
		 * it will proceed to the next element in the linked list and cycle through, until the linked list points
		 * at a null (meaning it has reached the end of the linked list) and then the while loop breaks.
		 */
		
		for (int i = 0; i < size; i++)
		{
			if (hashTable[i] != null)
			{
				counter += hashTable[i].size();
			}
		}
		
		return counter;
		
	}
	
	public int hashFunc(String key) {
		
		//this will give you the index to input into the hashtable
		long modifiedIndex = 0;
		
		//create an array of characters, of the same length as the key
		char[] keyChar = new char[key.length()];
		
		//populates the array that was just created. Now, the string key will be represented as an array of characters
		for (int i = 0; i < key.length(); i++)
		{
			keyChar[i] = key.charAt(i);
		}
		
		//come up with an initial x value for the polynomial function
		int x = 33;
		
		for (int i = 0; i < key.length()-1; i++)
		{
			modifiedIndex = ((modifiedIndex + (int)key.charAt(i)) * x) % size;
		}
		
		//casts it back to int in case the number was too large to fit
		int finalIndex = (int) modifiedIndex;
		return finalIndex;
	}
}
