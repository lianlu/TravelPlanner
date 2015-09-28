package Calculation;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ReadPrice {
	private String path;
	public ReadPrice(String fileName) {
		path = fileName;
	}
	public List<String> readPrice() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<String> flightPrice = new ArrayList<>();
		while((line = br.readLine()) != null) {
			flightPrice.add(line);
		}
		br.close();
		return flightPrice;
	}
}
