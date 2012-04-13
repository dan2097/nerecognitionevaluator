package dan2097.org.bitbucket.main;

import dan2097.org.bitbucket.annotationImplementations.CombinedTaggerNameProvider;
import dan2097.org.bitbucket.neRecognitionEvaluator.StatisticalEvaluator;

public class EvaluateCombinedTagger {

	public static void main(String[] args) {
		StatisticalEvaluator evaluator = new StatisticalEvaluator(new CombinedTaggerNameProvider());
		evaluator.evaluate();
	}
}
