package collectionsframework.beforegenerics.dictionary;

import java.util.Arrays;
import java.util.Objects;


public class HMap<K, V> {


	private static final int DEFAULT_CAPACITY=16;

	private final Node<K,V>[] table;


	public HMap(){
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public HMap(int capacity){

		this.table=new Node[capacity];

	}

	public V put(K key, V value){

		var newNode= new Node<>(hash(key), key, value);
		int index = index(key);

		var node=table[index];

		if(node==null){
			table[index]=newNode;
			return null;
		}else{
			while (node!=null){
				if(node.hash==hash(key) && Objects.equals(node.key,key)){
					 V oldValue=node.value;
					 node.value=value;
					 return oldValue;
				}
				node=node.next;
			}
		}
		return value;
	}

	private int hash(K key){
		return key.hashCode()>>>16;
	}

	private int index(K key){

		return hash(key)%DEFAULT_CAPACITY;
	}

	@Override
	public String toString() {
		return Arrays.stream(table).filter(kvNode ->Objects.nonNull(kvNode)).toString();
	}

	private static  class Node<K,V>{

		private int hash;
		private K key;
		private V value;

		Node<K,V> next;


		public Node(int hash, K key, V value) {
			this.hash = hash;
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return key +"=" +value;
		}
	}
}

class TestHMap{
	public static void main(String[] args) {
		HMap<Integer, String> hMap = new HMap<>();


	}

}
