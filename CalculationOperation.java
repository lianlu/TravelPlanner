package Calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculationOperation {
	private List<Integer> travelingPath = new ArrayList<>();
	private int travelingCost;
	
	public int getTravelingCost() {
		return this.travelingCost;
	}
	
	public List<Integer> getTravelingPath() {
		return this.travelingPath;
	}
	
	public void operateTSP(int[][] cost, int cityIndex, List<Integer> selectedCtsInd) {
		int n = cost.length;//total number of cities
		GetSubset s = new GetSubset();
		
		if(selectedCtsInd.size() == 1) {
			travelingPath.add(cityIndex);travelingPath.add(selectedCtsInd.get(0));travelingPath.add(cityIndex);
			travelingCost = cost[cityIndex][selectedCtsInd.get(0)] + cost[selectedCtsInd.get(0)][cityIndex];
			return;
		}
		List<List<Integer>> cities = s.subsets(selectedCtsInd);
		int[][] tsp = new int[n][cities.size()];//C(S,j)
		selectedCtsInd.add(cityIndex);
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[][] path = new ArrayList[n][cities.size()];
		//System.out.println(n);
		//initialization 1
		//int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < cities.size(); j++) {
				tsp[i][j] = Integer.MAX_VALUE;
				//count++;
			}
		}
		//System.out.println(count);
		//int cunt= 0;
		//initialization 2
		for(int j = 0; j < selectedCtsInd.size(); j++) {
			for(int i = 0; i < n; i++) {
				if(cities.get(j).contains(i) || i == cityIndex || !selectedCtsInd.contains(i)) {
					//System.out.println("i: " + i + ",j: " + j);
					continue;
				}
				//if(i == cityIndex) {System.out.println("cityindex" +"i:" +i + "j: "+ j); continue;}
				//if(!selectedCtsInd.contains(i)) {System.out.println("not included" + "i:" + i + "j:" + j);continue;}
				//cunt++;
				if(j == 0) {//first column, empty set, set size is zero
					tsp[i][j] = cost[i][cityIndex];
					path[i][j] = new ArrayList<Integer>(Arrays.asList(i,cityIndex));
					//cunt++;
					 
				} else {
					tsp[i][j] = cost[i][cities.get(j).get(0)] + cost[cities.get(j).get(0)][cityIndex];
					path[i][j] = new ArrayList<Integer>(Arrays.asList(i,cities.get(j).get(0),cityIndex));
					//cunt++;
				}
			}
		}
		//j, column; i, row; 
		//start from city i, traversing cities in the set with index j, ending at city cityIndex.
		for(int j = selectedCtsInd.size(); j < cities.size(); j++) {
			for(int i = 0; i < n; i++) {
				//if((i == 0 && j < cities.size()-1) || cities.get(j).contains(i)) {//means city 0 is the start city
				if((i == cityIndex && j < cities.size()-1) || cities.get(j).contains(i) || !selectedCtsInd.contains(i)) {
					//System.out.println("cityIndex " + "i: " + i + " j: " +j);
					continue;
				}
				//if(cities.get(j).contains(i)){System.out.println("get contains" + " i:" + i + " j: " + j);continue;}
				//if(!selectedCtsInd.contains(i)){System.out.println("not included " + "i: "+ i + " j:" + j);continue;}
				List<Integer> setIndex = cities.get(j);
				//for(int k = 0; k < cities.get(j).size(); k++) {
				for(int k = 0; k < setIndex.size(); k++) {	
					List<Integer> tmp = new ArrayList<Integer>(setIndex);
					tmp.remove(k);
					//tsp[i][j] = Math.min(cost[i][index.get(k)] + tsp[index.get(k)][cities.indexOf(tmp)], tsp[i][j]);
					if(tsp[i][j] > cost[i][setIndex.get(k)] + tsp[setIndex.get(k)][cities.indexOf(tmp)]) {
						tsp[i][j] = cost[i][setIndex.get(k)] + tsp[setIndex.get(k)][cities.indexOf(tmp)];
						path[i][j] = new ArrayList<Integer>(path[setIndex.get(k)][cities.indexOf(tmp)]);
						path[i][j].add(0,i);
					}
					
				}
			}
		}
		travelingPath = path[cityIndex][cities.size()-1];
		travelingCost = tsp[cityIndex][cities.size()-1];
	}
	
}

