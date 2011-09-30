package dan2097.org.bitbucket.main;

import java.io.IOException;

import dan2097.org.bitbucket.annotationImplementations.OpsinDocumentExtractorNameProvider;
import dan2097.org.bitbucket.neRecognitionEvaluator.StatisticalEvaluator;

public class EvaluateOpsinDocumentExtractor {

	public static void main(String[] args) throws IOException {
		StatisticalEvaluator evaluator = new StatisticalEvaluator(new OpsinDocumentExtractorNameProvider());
		evaluator.evaluate();
	}
}
