package Calculation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

public class StartJourney {

	public void startJourney(){
		CandidateCities city = new CandidateCities();
		CalculationOperation t = new CalculationOperation();
		int[][] cost;
		Set<String> selectedCts;
		List<Integer> selectedCtsInd;
		
		try {
			String coast = JOptionPane.showInputDialog(null, 
					"Welcom to Traveling Budget Calculation!\n\n"
					+ "Please specify where you'd like to travel: east or west coast?\n\n"
					+ "Please enter east or west:\n\n");
			coast = coast.replaceAll("\\s+", "").toLowerCase();
			
			while(coast == null || !("east".equals(coast)) || !("west".equals(coast))) {
				if(("east".equals(coast)) || ("west".equals(coast))) {
					break;
				}
				System.err.println("Sorry, please enter east or west.");
				coast = JOptionPane.showInputDialog(null, "Sorry, please enter east or west.");
				coast = coast.replaceAll("\\s+", "").toLowerCase();
			}
			
			String selectedCitiesInString = "";
			if(coast.equals("east")) {
				city.setCostAndMap("east");
				selectedCitiesInString = JOptionPane.showInputDialog(null, 
						"We have the following cities in our plan:\n"
						+ "New York, Boston, Washington, Philadelphia and Chicago.\n\n"
						+ "Please select cities you'd like to visit. \n"
						+ "Notice: 1.You'd better select more than one cities; \n"
						+ "2.Use comma \",\" to split.\n\n");
			} else if(coast.equals("west")){
				city.setCostAndMap("west");
				selectedCitiesInString = JOptionPane.showInputDialog(null, 
						"We have the following cities in our plan:\n"
						+ "Los Angeles, San Francisco, San Diego, Las Vegas, Seattle and Portland.\n\n"
						+ "Please select cities you'd like to visit. \n"
						+ "Notice: 1.You'd better select more than one cities; \n"
						+ "2.Use comma \",\" to split.\n\n");
			}
			
			String[] selectedCities = selectedCitiesInString.split(",");
			while(selectedCities.length <= 2 || selectedCities.length > 5) {
				if(coast.equals("east")) {
					selectedCitiesInString = JOptionPane.showInputDialog(null, 
							"We suggest you to select more than two cities from the following list, but do not repeat selecting!\n\n"
							+ "New York, Boston, Washington, Philadelphia and Chicago.\n\n");
				} else if(coast.equals("west")) {
					selectedCitiesInString = JOptionPane.showInputDialog(null, 
							"We suggest you to select more than two cities from the following list, but do not repeat selecting!\n\n"
							+ "Los Angeles, San Francisco, San Diego, Las Vegas, Seattle and Portland.\n\n");
				}
				selectedCities = selectedCitiesInString.split(",");
			}
			
			boolean goodSelection = false;
			while(!goodSelection) {
				for(int i = 0; i < selectedCities.length; i++) {
					if(!(city.getMapR().keySet().contains(selectedCities[i].replaceAll("\\s+", "").toLowerCase()))) {
						if(coast.equals("west")) {
						selectedCitiesInString = JOptionPane.showInputDialog(null, 
								"Please select the cities cities from the following list and use comma to \",\" split!\n"
								+ "Los Angeles, San Francisco, San Diego, Las Vegas, Seattle and Portland.");
						} else if(coast.equals("east")) {
							selectedCitiesInString = JOptionPane.showInputDialog(null, 
									"Please select the cities cities from the following list and use comma to \",\" split!\n"
									+ "New York, Boston, Washington, Philadelphia and Chicago.");
						}
						selectedCities = selectedCitiesInString.split(",");
						break;
					} else if(city.getMapR().keySet().contains(selectedCities[i].replaceAll("\\s+", "").toLowerCase())) {
						if(i == selectedCities.length-1) {
							goodSelection = true;
						}
					}
				}
			}
			
			selectedCts = new HashSet<>();
			for(int i = 0; i < selectedCities.length; i++){
				selectedCities[i] = selectedCities[i].replaceAll("\\s+", "").toLowerCase();
				selectedCts.add(selectedCities[i]);
			}
			
			StringBuilder citiesByUser = new StringBuilder();
			for(int i = 0; i < selectedCities.length; i++) {
				if(i < selectedCities.length-1) {
					citiesByUser.append(city.getMap().get(city.getMapR().get(selectedCities[i])) + ", ");
				} else {
					citiesByUser.append(city.getMap().get(city.getMapR().get(selectedCities[i])) + ".\n");
				}
			}
			
			String startCity = JOptionPane.showInputDialog(null, "You've selected the following cities in your trip:\n" 
																 + citiesByUser.toString() 
					   											 + "\nWhich city you would like to start ?");
			startCity = startCity.replaceAll("\\s+", "").toLowerCase();
			
			while(startCity == null || !selectedCts.contains(startCity.replaceAll("\\s+", "").toLowerCase())) {
				if(selectedCts.contains(startCity)) {
					break;
				}
				startCity = JOptionPane.showInputDialog(null, new String("Please select the cities from the list above!"));
				startCity = startCity.replaceAll("\\s+", "").toLowerCase();
			}
			int cityIndex = city.getMapR().get(startCity);
			JOptionPane.showMessageDialog(null, new String("Your journey will start from " + city.getMap().get(cityIndex) + "!"));
			
			cost = city.getCost();
			
			selectedCts.remove(startCity);
			selectedCtsInd = new ArrayList<>();
			for(String s : selectedCts) {
				selectedCtsInd.add(city.getMapR().get(s));
			}
			
			t.operateTSP(cost, cityIndex, selectedCtsInd);
			
			StringBuilder journeyRoute = new StringBuilder();
			journeyRoute.append("Total cost on the way will be " + t.getTravelingCost() + " dollars.\n\n");
			journeyRoute.append("Recommended traveling path is: \n\n");
			for(int i = 0; i < t.getTravelingPath().size(); i++) {
				String currentCity = city.getMap().get(t.getTravelingPath().get(i));
				if(i != t.getTravelingPath().size()-1) {
					journeyRoute.append(currentCity + " -> ");
				} else {
					journeyRoute.append(currentCity);
				}
			}
			JOptionPane.showMessageDialog(null, journeyRoute.toString() + "\n");
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "You quit the program!\n\nBye bye!\n\n" );
		}
	}
	public static void main(String[] args) {
		StartJourney journey = new StartJourney();
		journey.startJourney();
	}
}