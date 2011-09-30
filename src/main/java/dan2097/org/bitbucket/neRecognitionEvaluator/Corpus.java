package dan2097.org.bitbucket.neRecognitionEvaluator;

import java.util.List;

public class Corpus {
	private final String sentence;
	private final List<NamedEntity> expectedEntities;

	public Corpus(String sentence, List<NamedEntity> expectedEntities) {
		this.sentence = sentence;
		this.expectedEntities = expectedEntities;
	}

	public String getSentence() {
		return sentence;
	}

	public List<NamedEntity> getExpectedEntities() {
		return expectedEntities;
	}
}
