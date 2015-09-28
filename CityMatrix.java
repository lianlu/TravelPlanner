package Calculation;

import java.io.IOException;
import java.util.List;

public class CityMatrix {
	private List<String> priceList;
	private ReadPrice priceReader;
	private int numberOfCities;
	public CityMatrix(String coast) {
		if(coast.equals("east")) {
			numberOfCities = 5;
			priceReader = new ReadPrice("/Users/Jason/Documents/workspace/TravelingBudgetCalculation/src/Calculation/SWSpider/prices/east_prices");
		} else {
			numberOfCities = 6;
			priceReader = new ReadPrice("/Users/Jason/Documents/workspace/TravelingBudgetCalculation/src/Calculation/SWSpider/prices/west_prices");
		}
	}
	public static void main(String[] args) throws IOException {
		CityMatrix cm = new CityMatrix("west");
		for(int[] ar : cm.getPrice()) {
			for(int i : ar) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	public int[][] getPrice() {
		try {
			priceList = priceReader.readPrice();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[][] priceBetweenCity = new int[numberOfCities][numberOfCities];
		for(String s : priceList) {
			int x = Character.getNumericValue(s.charAt(2));
			int y = Character.getNumericValue(s.charAt(6));
			int price = Integer.parseInt(s.substring(14));
			priceBetweenCity[x][y] = price;
		}
		return priceBetweenCity;
	}
}
