package co.grandcircus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<String,Double> menu = new HashMap<>();
		menu.put("apple", 0.99);
		menu.put("orange", 0.59);
		menu.put("watermelon", 5.00);
		menu.put("deli meat", 6.99);
		menu.put("lentil soup", 4.67);
		menu.put("whiskey", 45.99);
		menu.put("pomagranate", 2.99);
		menu.put("grapefruit", 3.49);
		ArrayList<String> items = new ArrayList<>(Arrays.asList("apple", "orange", "watermelon","deli meat","lentil soup","whiskey","pomagranate","grapefruit"));
		ArrayList<Double> prices = new ArrayList<>(Arrays.asList(0.99,0.59,5.00,6.99,4.67,45.99,2.99,3.49));
		ArrayList<String> orders = new ArrayList<>();
		
		displayMenu(menu);
		String cont = "y";
		String order = "";

		while (cont.equals("y")) {
			// your code should start here!
			System.out.println();
			order = Validator.getString(scan, "What item would you like to order? ");
			if (items.contains(order)) {
				orders.add(order);
				System.out.println("Adding " + order + " to cart at " + prices.get(items.indexOf(order)));
			} else {
				while(!items.contains(order)) {
					System.out.println("Sorry, we don't have those.  Please try again.");
					displayMenu(menu);
					System.out.println();
					order = Validator.getString(scan, "What item would you like to order? ");
				}
				orders.add(order);
				System.out.println("Adding " + order + " to cart at " + prices.get(items.indexOf(order)));
			}

			// your logic shoulds stop here if it doesn't need to be included in the loop
			System.out.println("Would you like to order anything else (y/n)");
			cont = scan.nextLine();
		}
		

		//This is our indication that the program has ended
		printReceipt(orders, prices, items);
		System.out.printf("Average price per item in order was $" + "%.2f", getAverage(orders, prices, items));
		scan.close();	

	}
	private static void displayMenu(Map<String,Double> menu) {
		System.out.println("Welcome to Krieg's Food Market!\n");
		System.out.printf("%-10s %12s\n","Item", "Price");
		System.out.println("===================================");
		for(String item : menu.keySet()) {
			System.out.printf("%-20s $%-12.2f\n", item, menu.get(item));
		}
	}
	private static void printReceipt(ArrayList<String> orders, ArrayList<Double> prices, ArrayList<String> items) {
		System.out.println("Here's what you got:\n");
		System.out.printf("%-10s %12s\n","Item", "Price");
		System.out.println("===================================");
		for(String item : orders) {
			System.out.printf("%-20s $%-12.2f\n", item, prices.get(items.indexOf(item)));
		}
	}
	private static double getAverage(ArrayList<String> orders, ArrayList<Double> prices, ArrayList<String> items) {
		double sum = 0;
		for(String item : orders) {
			sum += prices.get(items.indexOf(item));
		}
		return sum / orders.size();
	}

}
