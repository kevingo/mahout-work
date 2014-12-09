package recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommend {
	
	public static void main(String [] args) {
		
		DataModel dm = null;
		UserSimilarity sim = null;
		UserNeighborhood neighbor = null;
		UserBasedRecommender recommend = null;
		
		UserBasedRecommend rec = new UserBasedRecommend();
		try {
			dm = new FileDataModel(new File("dataset.csv"));
			sim = new PearsonCorrelationSimilarity(dm);
			neighbor = new ThresholdUserNeighborhood(0.1, sim, dm);
			recommend = new GenericUserBasedRecommender(dm, neighbor, sim);
			
			List<RecommendedItem> recommendations = recommend.recommend(2, 3);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}
			
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
