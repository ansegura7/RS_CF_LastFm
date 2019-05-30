package edu.uniandes.mine.rs.test;

import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;

import edu.uniandes.mine.rs.Utilities;

// Test Main Class
public class LastfmValidation {
	
	// Class constants
	private final static double trainSize = 0.80;
	private static double testSize = (1 - trainSize);
	
	// Main function
	public static void main(String[] args) throws IOException, TasteException {
		Utilities.showMessage("Start process");
		String rsType = "USER";
		
		// Create data model
		String filename = "data/in/u.data.csv";
		DataModel model = Utilities.createDataModel(filename);
		
		// Evaluator variables
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = null;
		double result;
		
		if (rsType.equals("USER")) {
			Utilities.showMessage("User-based recommendation test");
			
			builder = new CFRecommenderBuilder.UserJaccardRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Jaccard MAD: " + result + ", with learning size of " + trainSize + " and test size of " + testSize);
			
			builder = new CFRecommenderBuilder.UserCosineRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Cosine MAD: " + result + ", with learning size  of " + trainSize + " and test size of " + testSize);
			
			builder = new CFRecommenderBuilder.UserPearsonRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Pearson MAD: " + result + ", with learning size  of " + trainSize + " and test size of " + testSize);
		}
		else if (rsType.equals("ITEM")) {
			Utilities.showMessage("Item-based recommendation test");
			
			builder = new CFRecommenderBuilder.ItemJaccardRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Jaccard MAD: " + result + ", with learning size of " + trainSize + " and test size of " + testSize);
			
			builder = new CFRecommenderBuilder.ItemCosineRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Cosine MAD: " + result + ", with learning size of " + trainSize + " and test size of " + testSize);
			
			builder = new CFRecommenderBuilder.ItemPearsonRecommender();
			result = evaluator.evaluate(builder, null, model, trainSize, testSize);
			Utilities.showMessage("Pearson MAD: " + result + ", with learning size of " + trainSize + " and test size of " + testSize);
		}
		
		Utilities.showMessage("End process");
	}
	
}