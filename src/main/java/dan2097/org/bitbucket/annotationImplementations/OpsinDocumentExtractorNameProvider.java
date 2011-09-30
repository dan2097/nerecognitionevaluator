package dan2097.org.bitbucket.annotationImplementations;

import java.util.ArrayList;
import java.util.List;

import org.bitbucket.dan2097.structureExtractor.DocumentToStructures;
import org.bitbucket.dan2097.structureExtractor.IdentifiedChemicalName;

import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntityProvider;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity;
public class OpsinDocumentExtractorNameProvider implements NamedEntityProvider {

	public List<NamedEntity> findNamedEntities(String input) {
		List<NamedEntity> chemicalEntities = new ArrayList<NamedEntity>();
		List<IdentifiedChemicalName> identifiedNames =  new DocumentToStructures(input).extractNames();
		for (IdentifiedChemicalName identifiedChemicalName : identifiedNames) {
			chemicalEntities.add(new NamedEntity(identifiedChemicalName.getTextValue(), identifiedChemicalName.getStart(), identifiedChemicalName.getNameType().toString()));
		}
		return chemicalEntities;
	}
}
