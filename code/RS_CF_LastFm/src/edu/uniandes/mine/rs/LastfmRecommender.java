package edu.uniandes.mine.rs;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

// Program Main Class of Lastfm Recommender
public class LastfmRecommender {
	
	// Main function
	public static void main(String[] args) throws IOException, TasteException {
		Utilities.showMessage("Start process");
		
		// Declare configuration parameters
		int userID = 1;
		String infile = "data/in/u.data.csv";
		String outfile = "data/out/output.txt";
		String rsType = "USER";
		String simType = "COSINE";
		int knn = 51;
		int nRecommendations = 10;
		
		// Read configuration parameters (userID, infile, outfile, recType, rsType, knn, nNeighborhood)
		if (args.length > 0) {
			userID = Integer.parseInt(args[0]);
			
			if (args.length > 1) {
				infile = args[1];
				
				if (args.length > 2) {
					outfile = args[2];
					
					if (args.length > 3) {
						rsType = args[3];
						
						if (args.length > 4) {
							simType = args[4];
							
							if (args.length > 5) {
								knn = Integer.parseInt(args[5]);
								
								if (args.length > 6) {
									nRecommendations = Integer.parseInt(args[6]);
								}
							}
						}
					}
				}
			}
		}
		
		// Make the recommendations
		if (rsType.equals("USER") || rsType.equals("ITEM")) {
			
			// Create data model
			DataModel model = Utilities.createDataModel(infile);
			
			if (model != null) {
				
				if (rsType.equals("USER")) {
					userRecommendation(userID, model, simType, knn, nRecommendations, outfile);
				}
				else {
					itemRecommendation(userID, model, simType, nRecommendations, outfile);
				}
				
			}
		}
		
		Utilities.showMessage("End process");
	}
	
	// Function that makes the recommendation by users
	private static void userRecommendation(int userID, DataModel model, String simType, int knn, int nRecommendations, String outfile) throws TasteException {
		long startTime = System.currentTimeMillis();
		long estimatedTime;
		
		// List of recommendations
		List<RecommendedItem> recommendations;
		
		// Method Nearest N User Neighborhood
		Utilities.showMessage("Method Nearest N User Neighborhood (" + knn + ")");
		
		if (simType.equals("JACCARD")) {
			
			Utilities.showMessage("Recommended items using Jaccard Index (Tanimoto Coefficient) to user_id: " + userID);
			UserSimilarity similarity1 = new TanimotoCoefficientSimilarity(model);
			recommendations = getUserBasedItems(userID, model, similarity1, knn, nRecommendations);
			System.out.println("Recommendations size: " + recommendations.size() + ", nRecommendations: " + nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
		else if (simType.equals("COSINE")) {
			
			Utilities.showMessage("Recommended items using Uncentered Cosine similarity to user_id: " + userID);
			UserSimilarity similarity2 = new UncenteredCosineSimilarity(model);
			recommendations = getUserBasedItems(userID, model, similarity2, knn, nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
		else if (simType.equals("PEARSON")) {
			
			Utilities.showMessage("Recommended items using Pearson Correlation similarity to user_id: " + userID);
			UserSimilarity similarity3 = new PearsonCorrelationSimilarity(model);
			recommendations = getUserBasedItems(userID, model, similarity3, knn, nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
	}
	
	// Function that makes the recommendation by items
	private static void itemRecommendation(int userID, DataModel model, String simType, int nRecommendations, String outfile) throws TasteException {
		long startTime = System.currentTimeMillis();
		long estimatedTime;
		
		// List of recommendations
		List<RecommendedItem> recommendations;
		
		if (simType.equals("JACCARD")) {
			
			Utilities.showMessage("Recommended items using Jaccard Index (Tanimoto Coefficient) to user_id: " + userID);
			ItemSimilarity similarity1 = new TanimotoCoefficientSimilarity(model);
			recommendations = getItemBasedItems(userID, model, similarity1, nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
		else if (simType.equals("COSINE")) {
			
			Utilities.showMessage("Recommended items using Uncentered Cosine similarity to user_id: " + userID);
			ItemSimilarity similarity2 = new UncenteredCosineSimilarity(model);
			recommendations = getItemBasedItems(userID, model, similarity2, nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
		else if (simType.equals("PEARSON")) {
			
			Utilities.showMessage("Recommended items using Pearson Correlation similarity to user_id: " + userID);
			ItemSimilarity similarity3 = new PearsonCorrelationSimilarity(model);
			recommendations = getItemBasedItems(userID, model, similarity3, nRecommendations);
			estimatedTime = System.currentTimeMillis() - startTime;
			Utilities.saveItemList(userID, recommendations, outfile, estimatedTime);
		}
	}
	
	// Returns recommended items using User-based similarity methods
	public static List<RecommendedItem> getUserBasedItems(int userID, DataModel model, UserSimilarity similarity, int knn, int nRecommendations) {
		List<RecommendedItem> recommendations = null;
		
		try {
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(knn, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recommendations = recommender.recommend(userID, nRecommendations);
			
		} catch (TasteException e) {
			Utilities.showMessage("ERROR: " + e.getMessage());
		}
		
		return recommendations;
	}
	
	// Returns recommended items using Item-based similarity methods
	public static List<RecommendedItem> getItemBasedItems(int userID, DataModel model, ItemSimilarity similarity, int nRecommendations) {
		List<RecommendedItem> recommendations = null;
		
		try {
			ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
			recommendations = recommender.recommend(userID, nRecommendations);
			
		} catch (TasteException e) {
			Utilities.showMessage("ERROR: " + e.getMessage());
		}
		
		return recommendations;	
	}
	
}