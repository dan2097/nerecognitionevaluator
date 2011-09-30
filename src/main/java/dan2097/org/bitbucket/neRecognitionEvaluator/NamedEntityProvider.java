package dan2097.org.bitbucket.neRecognitionEvaluator;

import java.util.List;

public interface NamedEntityProvider {
	List<NamedEntity> findNamedEntities(String input);
}
