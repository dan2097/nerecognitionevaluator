package dan2097.org.bitbucket.annotationImplementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import uk.ac.cam.ch.wwmm.oscar.Oscar;
import uk.ac.cam.ch.wwmm.oscar.chemnamedict.core.ChemNameDictRegistry;
import uk.ac.cam.ch.wwmm.oscar.ont.OntologyTerms;
import uk.ac.cam.ch.wwmm.oscar.types.NamedEntityType;
import uk.ac.cam.ch.wwmm.oscarMEMM.MEMMRecogniser;
import uk.ac.cam.ch.wwmm.oscarMEMM.models.ChemPapersModel;
import uk.ac.cam.ch.wwmm.oscarrecogniser.interfaces.ChemicalEntityRecogniser;
import uk.ac.cam.ch.wwmm.oscarrecogniser.saf.StandoffResolver.ResolutionMode;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntityProvider;

public class Oscar4NameProvider implements NamedEntityProvider {

	public static Oscar oscar4 = new Oscar();
	public static ChemicalEntityRecogniser recogniser = new MEMMRecogniser(new ChemPapersModel(), OntologyTerms.getDefaultInstance(), new ChemNameDictRegistry(Locale.ENGLISH));

	public List<NamedEntity> findNamedEntities(String input) {
		List<NamedEntity> chemicalEntities = new ArrayList<NamedEntity>();
		List<uk.ac.cam.ch.wwmm.oscar.document.NamedEntity> identifiedEntities = recogniser.findNamedEntities(oscar4.tokenise(input), ResolutionMode.REMOVE_BLOCKED);
		for (uk.ac.cam.ch.wwmm.oscar.document.NamedEntity namedEntity : identifiedEntities) {
			if (!namedEntity.getType().isInstance(NamedEntityType.COMPOUND)){
				continue;
			}
			chemicalEntities.add(new NamedEntity(namedEntity.getSurface(), namedEntity.getStart(), namedEntity.getType().toString()));
		}
		return chemicalEntities;
	}
}
