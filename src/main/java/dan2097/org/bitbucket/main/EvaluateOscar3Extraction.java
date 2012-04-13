package dan2097.org.bitbucket.main;

import java.io.IOException;

import dan2097.org.bitbucket.annotationImplementations.Oscar3NameProvider;
import dan2097.org.bitbucket.neRecognitionEvaluator.StatisticalEvaluator;

public class EvaluateOscar3Extraction {

	public static void main(String[] args) throws IOException {
		StatisticalEvaluator evaluator = new StatisticalEvaluator(new Oscar3NameProvider());
		evaluator.evaluate();
	}
}
