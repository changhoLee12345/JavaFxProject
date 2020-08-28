package collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

interface GetMap<T, S> {
	public void show(T t, S s);
}

public class MapExample {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Hong", 10);
		map.put("Hwang", 9);
		map.put("Park", 8);

		showItems(map);
		
		GetMap<Integer, String> gm = new GetMap<Integer, String>() {

			@Override
			public void show(Integer t, String s) {
				System.out.println(t + ", " + s);
			}
			
		};
		gm.show(30, "Hwang");

	}

	public static void showItems(Map<String, Integer> map) {
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			Integer value = map.get(key);
			System.out.println("key: " + key + ", value: " + value);
		}
	}
}
