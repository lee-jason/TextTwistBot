import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class MultiValueMap<K extends Comparable<K>, V> extends HashMap<K, Collection<V>>{
	
	public MultiValueMap(){
		super();
	}
	
	public V put(K key, V value){
		if(this.get(key) != null){
			this.get(key).add(value);
		}
		else{
			LinkedList<V> newList = new LinkedList<V>();
			newList.add(value);
			this.put(key, newList);
		}
		return value;
	}
	
	public Collection<V> getCollection(K key){
		return super.get(key);
	}
	
}
