package edu.uniandes.mine.rs.test;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

// Collaborative Filtering Recommender builder - Classes container
public class CFRecommenderBuilder {
	
	// Class constants
	public final static int KK = 101;
	
	// Interface User-based Recommender with Jaccard similarity
	public static class UserJaccardRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	UserSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
	    	UserNeighborhood neighborhood = new NearestNUserNeighborhood(KK, similarity, dataModel);
	        return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
	    }
	}

	// Interface User-based Recommender with Cosine similarity
	public static class UserCosineRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	UserSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
	    	UserNeighborhood neighborhood = new NearestNUserNeighborhood(KK, similarity, dataModel);
	        return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
	    }
	}
	
	// Interface User-based Recommender with Pearson similarity
	public static class UserPearsonRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
	    	UserNeighborhood neighborhood = new NearestNUserNeighborhood(KK, similarity, dataModel);
	        return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
	    }
	}
	
	// Interface Item-based Recommender with Jaccard similarity
	public static class ItemJaccardRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	ItemSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
	        return new GenericItemBasedRecommender(dataModel, similarity);
	    }
	}
	
	// Interface Item-based Recommender with Cosine similarity
	public static class ItemCosineRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	ItemSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
	        return new GenericItemBasedRecommender(dataModel, similarity);
	    }
	}
	
	// Interface Item-based Recommender with Pearson similarity
	public static class ItemPearsonRecommender implements RecommenderBuilder {
		
	    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
	    	ItemSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
	        return new GenericItemBasedRecommender(dataModel, similarity);
	    }
	}
}