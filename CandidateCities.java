package Calculation;

import java.util.Map;
import java.util.HashMap;

public class CandidateCities {
	private  int[][] cities;
	private  Map<Integer, String> map;
	private  Map<String, Integer> map_r;
	
	public CandidateCities() {
		//map = HashBiMap.create();
		map = new HashMap<>();
		map_r = new HashMap<>();
	}
	
	public void setCostAndMap(String s) {
		if(s.equals("east")) {
			CityMatrix cm = new CityMatrix(s);
			cities = cm.getPrice();
//			cities = new int[][]{
//										{0,104,112,193,134},
//										{104,0,139,113,160},
//										{112,99,0,236,188},
//										{221,161,351,0,285},
//										{156,160,188,285,0}};
			map.clear();
			map.put(0, "New York");
			map.put(1, "Boston");
			map.put(2, "Washington");
			map.put(3, "Philadelphia");
			map.put(4, "Chicago");
			
			map_r.clear();
			map_r.put("newyork", 0);
			map_r.put("boston", 1);
			map_r.put("washington", 2);
			map_r.put("philadepphia", 3);
			map_r.put("chicago", 4);
			
		} else if(s.equals("west")) {
			CityMatrix cm = new CityMatrix(s);
			cities = cm.getPrice();
//			cities = new int[][]{
//										{0,141,204,130,177,186},
//										{130,0,151,92,134,161},
//										{280,158,0,122,184,157},
//										{130,187,155,0,252,192},
//										{169,134,144,176,0,123},
//										{150,161,122,136,123,0}};
			map.clear();
			map.put(0, "Los Angeles");
			map.put(1, "San Francisco");
			map.put(2, "San Diego");
			map.put(3, "Las Vegas");
			map.put(4, "Seattle");
			map.put(5, "Portland");
			
			map_r.clear();
			map_r.put("losangeles", 0);
			map_r.put("sanfrancisco", 1);
			map_r.put("sandiego", 2);
			map_r.put("lasvegas", 3);
			map_r.put("seattle", 4);
			map_r.put("portland", 5);
			
		}
	}
	public int[][] getCost() {
		return cities;
	}
	public Map<Integer, String> getMap() {
		return map;
	}
	public Map<String, Integer> getMapR() {
		return map_r;
	}
}

