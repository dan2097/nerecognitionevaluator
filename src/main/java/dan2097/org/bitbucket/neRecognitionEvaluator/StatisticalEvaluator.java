package dan2097.org.bitbucket.neRecognitionEvaluator;

import java.util.List;

public class StatisticalEvaluator {

	private final NamedEntityProvider namedEntityProvider;
	
	public StatisticalEvaluator(NamedEntityProvider namedEntityProvider) {
		this.namedEntityProvider =namedEntityProvider;
	}
	public void evaluate() {
		List<Corpus> corpora = CorpusGenerator.generateCorpora();
		int truePositives = 0;
		int falsePositives = 0;
		int falseNegatives = 0;
		
		for (Corpus corpus : corpora) {
			List<NamedEntity> foundNamedEntities = namedEntityProvider.findNamedEntities(corpus.getSentence());
			for (NamedEntity entity : corpus.getExpectedEntities()) {
				boolean wasFound = false;
				for (int i = 0; i < foundNamedEntities.size(); i++) {
					NamedEntity foundNamedEntity = foundNamedEntities.get(i);
					if (entity.getSurface().equals(foundNamedEntity.getSurface()) && entity.getStartPosition() == foundNamedEntity.getStartPosition()){
						wasFound =true;
						foundNamedEntities.remove(i);
						break;
					}
				}
				if (wasFound){
					truePositives++;
				}
				else{
					falseNegatives++;
				}
			}
			falsePositives += foundNamedEntities.size();
		}
		int totalEntitiesExpected = truePositives + falseNegatives;
		double recall = (double)truePositives/(double)totalEntitiesExpected;
		int totalEntitiesFound = truePositives + falsePositives;
		double precision = (double)truePositives/(double) totalEntitiesFound;
		double fscore = (2* precision * recall)/(precision + recall);
		System.out.println(recall);
		System.out.println(precision);
		System.out.println(fscore);
	}
}
