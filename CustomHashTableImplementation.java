import java.util.Vector;

/**
 * @author : Harsh Verma
 * @Created on : 16-feb-2019 ,  1:30 AM
 */

public class Main {

	/**
	 * Custom Class for Storing key, value format data
	 * @param <K> defines the Key
	 * @param <V> defines the value
	 */
	public static class DataNodeHavingKeyAndValue<K,V> {
		// Taking Generic Type key and value
		K key;
		V value;
		DataNodeHavingKeyAndValue<K, V> next;

		public DataNodeHavingKeyAndValue() {
			// String key and Value
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * Custom HashMap class which stores the bucketList to be treated as HashTable
	 * @param <K> defines the Key
	 * @param <V> defines the value
	 */
	public static class CustomHashMap<K,V> {

		// Setting Initial Bucket List to 10 then if the capacity exceded it increases the vector size by 10 more
		Vector<DataNodeHavingKeyAndValue<K, V>> bucketList = new Vector<>(10,10);
		int totalNumberOfBucketsAtInitial = 10;
		int size;

		// Initializing Custom HashMap for all initial values as Null

		/**
		 * Defalut Constructor of Custom HashMap
		 */
		public CustomHashMap() {
			for (int index = 0; index < totalNumberOfBucketsAtInitial; index++) {
				bucketList.add(null);
			}
		}

		// Helper Function to fetch the index of key in bucket List
		private int getBucketIndexInBucketList(K key) {
			int hashCodeCalculated = key.hashCode();
			return hashCodeCalculated % totalNumberOfBucketsAtInitial;
		}



		/**
		 * // Helper function to getValueFromBucketListBasedOnGivenKeyInput the value from a given Key
		 * Function to fetch a value for a given Key
		 * @param key Defines the Key
		 * @return the Value based on the provided input Key
		 */
		public V getValueFromBucketListBasedOnGivenKeyInput(K key) {
			int index = getBucketIndexInBucketList(key);
			DataNodeHavingKeyAndValue<K, V> firstCustomNode = bucketList.get(index);
			while (firstCustomNode != null) {
				if (firstCustomNode.key.equals(key)) {
					return firstCustomNode.value;
				}
				firstCustomNode = firstCustomNode.next;
			}
			return null;
		}


		/**
		 * Remove the Node from buckeyList based on provided input key
		 * @param key : Defines key
		 * @return : The value if its deleted else returns null
		 */
		public V remove(K key) {
			int indexInBucket = getBucketIndexInBucketList(key);
			DataNodeHavingKeyAndValue<K, V> firstCustomNode = bucketList.get(indexInBucket);

			// If First Node is null return null
			if (firstCustomNode == null) {
				return null;
			}

			// If key matches return the key and reduce the size of bucketList
			if (firstCustomNode.key.equals(key)) {
				V valueInNode = firstCustomNode.value;
				firstCustomNode = firstCustomNode.next;
				bucketList.set(indexInBucket, firstCustomNode);
				size--;
				return valueInNode;
			}

			else {
				DataNodeHavingKeyAndValue<K, V> prev = null;
				while (firstCustomNode != null) {

					if (firstCustomNode.key.equals(key)) {
						prev.next = firstCustomNode.next;
						size--;
						return firstCustomNode.value;
					}
					prev = firstCustomNode;
					firstCustomNode = firstCustomNode.next;
				}
				size--;
				return null;
			}
		}

		/**
		 * Function to add Key and Value in bucket List
		 * @param key : Defines Key
		 * @param value : Defines value corresponding to given input key
		 */
		public void addkeyInBucket(K key, V value) {
			int index = getBucketIndexInBucketList(key);
			DataNodeHavingKeyAndValue<K, V> firstCustomNode = bucketList.get(index);
			DataNodeHavingKeyAndValue<K, V> dataNodeToBeAdded = new DataNodeHavingKeyAndValue<>();
			dataNodeToBeAdded.key = key;
			dataNodeToBeAdded.value = value;

			// If Node is null add at start and increment size
			if (firstCustomNode == null) {
				bucketList.set(index, dataNodeToBeAdded);
				size++;

			}

			else {
				while (firstCustomNode != null) {
					if (firstCustomNode.key.equals(key)) {
						firstCustomNode.value = value;
						size++;
						break;
					}
					firstCustomNode = firstCustomNode.next;
				}
				if (firstCustomNode == null) {
					firstCustomNode = bucketList.get(index);
					dataNodeToBeAdded.next = firstCustomNode;
					bucketList.set(index, dataNodeToBeAdded);
					size++;
				}
			}

			System.out.println("Succesfully added,  key : " + key + ", value : " + value);
		}
	}


	/**
	 * Maun Function to Test The Given Program
	 * @param args : Runtime argument if provided
	 */
	public static void main(String[] args) {
		System.out.println("Implementation of Hash Table in Java!");
		CustomHashMap<String,Integer> map=new CustomHashMap<>();
		map.addkeyInBucket("one", 1);
		map.addkeyInBucket("two", 2);
		map.addkeyInBucket("three", 3);
		map.addkeyInBucket("four", 4);
		map.addkeyInBucket("five", 5);
		map.addkeyInBucket("six", 6);
		map.addkeyInBucket("seven", 7);
		map.addkeyInBucket("eight", 8);
		map.addkeyInBucket("nine", 9);
		map.addkeyInBucket("ten", 10);
		// The Vector will double itself at this point of insertion as it will exceed the initial 10 index storage
		map.addkeyInBucket("fifty", 50);
		map.addkeyInBucket("sixty", 60);
		map.addkeyInBucket("ninty", 90);
		map.addkeyInBucket("zero", 0);
		map.addkeyInBucket("minus", -1);

		System.out.println("Succesfully deleted : " + map.remove("one"));
		System.out.println("Succesfully deleted : " + map.remove("one"));
		System.out.println("Succesfully deleted : " + map.remove("seven"));
	}
}
