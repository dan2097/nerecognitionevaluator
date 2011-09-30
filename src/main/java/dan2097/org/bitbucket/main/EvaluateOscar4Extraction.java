package dan2097.org.bitbucket.main;

import java.io.IOException;

import dan2097.org.bitbucket.annotationImplementations.Oscar4NameProvider;
import dan2097.org.bitbucket.neRecognitionEvaluator.StatisticalEvaluator;

public class EvaluateOscar4Extraction {

	public static void main(String[] args) throws IOException {
		StatisticalEvaluator evaluator = new StatisticalEvaluator(new Oscar4NameProvider());
		evaluator.evaluate();
	}
}
