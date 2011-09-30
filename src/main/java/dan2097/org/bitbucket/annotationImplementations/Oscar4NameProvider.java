package dan2097.org.bitbucket.annotationImplementations;

import java.util.ArrayList;
import java.util.List;

import uk.ac.cam.ch.wwmm.oscar.Oscar;
import uk.ac.cam.ch.wwmm.oscar.types.NamedEntityType;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntityProvider;

public class Oscar4NameProvider implements NamedEntityProvider {

	public static Oscar oscar4 = new Oscar();

	public List<NamedEntity> findNamedEntities(String input) {
		List<NamedEntity> chemicalEntities = new ArrayList<NamedEntity>();
		List<uk.ac.cam.ch.wwmm.oscar.document.NamedEntity> identifiedEntities = oscar4.findNamedEntities(input);
		for (uk.ac.cam.ch.wwmm.oscar.document.NamedEntity namedEntity : identifiedEntities) {
			if (!namedEntity.getType().isInstance(NamedEntityType.COMPOUND)
					&& !namedEntity.getType().isInstance(NamedEntityType.ASE)){
				continue;
			}
			chemicalEntities.add(new NamedEntity(namedEntity.getSurface(), namedEntity.getStart(), namedEntity.getType().toString()));
		}
		return chemicalEntities;
	}
}
