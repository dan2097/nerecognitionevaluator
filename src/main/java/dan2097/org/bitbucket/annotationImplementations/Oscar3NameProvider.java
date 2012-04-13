package dan2097.org.bitbucket.annotationImplementations;

import java.util.ArrayList;
import java.util.List;

import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntityProvider;

public class Oscar3NameProvider implements NamedEntityProvider{
//OSCAR3 and OSCAR4 use incompatible versions on OpenNLP
	public List<dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity> findNamedEntities(String input) {
		try{
			List<NamedEntity> chemicalEntities = new ArrayList<NamedEntity>();
//			SciXMLDocument sciXmlDoc = TextToSciXML.textToSciXML(input);
//			MEMMRecogniser recog = new MEMMRecogniser();
//			ProcessingDocument procDoc = ProcessingDocumentFactory.getInstance().makeTokenisedDocument(new Document((Element)XOMTools.safeCopy(sciXmlDoc.getRootElement())), false, false, false);
//			List<uk.ac.cam.ch.wwmm.oscar3.recogniser.document.NamedEntity> identifiedEntities = recog.findNamedEntities(procDoc);
//			for (uk.ac.cam.ch.wwmm.oscar3.recogniser.document.NamedEntity namedEntity : identifiedEntities) {
//				if (namedEntity.getType().equals("CM")){
//					if (namedEntity.isBlocked()){
//						continue;
//					}
//					chemicalEntities.add(new NamedEntity(namedEntity.getSurface(), namedEntity.getStart(), namedEntity.getType()));
//				}
//			}
			return chemicalEntities;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
