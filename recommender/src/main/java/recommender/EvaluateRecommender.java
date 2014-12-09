package recommender;

import java.io.File;
import java.io.IOException;

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
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class EvaluateRecommender{
	
	static DataModel model = null;
	
	public static void main(String [] args) {
		try {
			model = new FileDataModel(new File("dataset.csv"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new RecommenderBuilder() {

			public Recommender buildRecommender(DataModel dataModel)
					throws TasteException {
				DataModel model = null;
				try {
					model = new FileDataModel(new File("dataset.csv"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
				UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
				return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
			}
			
		};
		double result = 0;
		try {
			result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
		} catch (TasteException e) {
			e.printStackTrace();
		}
		System.out.println(result);

	}
	
	
}
