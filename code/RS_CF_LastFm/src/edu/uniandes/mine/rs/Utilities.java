package edu.uniandes.mine.rs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

// This class contains static utility functions
public class Utilities {
	
	// Class constants
	public final static boolean debug = true;
	public final static String separator = "|";
	
	// Function that create data mode if file exists
	public static DataModel createDataModel(String filename) {
		DataModel model = null;
		
		try {
			// Creating Data Model from data
			model = new FileDataModel(new File(filename));
			showMessage("Model loaded - Num Users: "+ model.getNumUsers() + ", Num Items: " + model.getNumItems());
			
		} catch (FileNotFoundException e) {
			model = null;
			showMessage(e.getMessage());
		
		} catch (Exception e) {
			model = null;
			showMessage(e.getMessage());
		}
		
		return model;
	}
	
	// Show into a console the list of recommended items
	public static void showItemList(Integer userID, List<RecommendedItem> recommendations) {
		
		if (recommendations != null) {
			String strLine = "";
			
			for (RecommendedItem item : recommendations) {
				strLine = userID + separator + item.getItemID() + separator + item.getValue();	
				showMessage(strLine);
			}
		}
	}
	
	// Save into a plain file the list of recommended items
	public static void saveItemList(Integer userID, List<RecommendedItem> recommendations, String output, long estimatedTime) {
		
		if (recommendations != null) {
			
			// Create file
			File oFile = new File(output);
			BufferedWriter writer;
			String strLine = "";
			
			try {
				writer = new BufferedWriter(new FileWriter(oFile));
				writer.write("BEGIN");
				writer.newLine();
				
				for (RecommendedItem item : recommendations) {
					strLine = userID + separator + item.getItemID() + separator + item.getValue();
					
					writer.write(strLine);
					writer.newLine();
					showMessage(strLine);
				}
				
				writer.write("ELAPSED_TIME(ms)");
				writer.newLine();
				writer.write(Long.toString(estimatedTime));
				writer.newLine();
				writer.write("END");
				
				// Close file
				writer.close();
				
			} catch (IOException e) {
				showMessage("ERROR: " + e.getMessage());
			}
			finally {
				writer = null;
			}
		}
	}
	
	// Show an audit message into a console
	public static void showMessage(String msg) {
		
		if (debug) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSSS a");
			String formattedDate = sdf.format(date);
			
			System.out.println(">> " + msg + ". [Datestamp: " + formattedDate + "]");
		}
	}
	
}