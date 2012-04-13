package dan2097.org.bitbucket.annotationImplementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bitbucket.dan2097.structureExtractor.DocumentToStructures;
import org.bitbucket.dan2097.structureExtractor.IdentifiedChemicalName;

import uk.ac.cam.ch.wwmm.oscar.Oscar;
import uk.ac.cam.ch.wwmm.oscar.chemnamedict.core.ChemNameDictRegistry;
import uk.ac.cam.ch.wwmm.oscar.document.Token;
import uk.ac.cam.ch.wwmm.oscar.document.TokenSequence;
import uk.ac.cam.ch.wwmm.oscar.ont.OntologyTerms;
import uk.ac.cam.ch.wwmm.oscar.types.NamedEntityType;
import uk.ac.cam.ch.wwmm.oscarMEMM.MEMMRecogniser;
import uk.ac.cam.ch.wwmm.oscarMEMM.models.ChemPapersModel;
import uk.ac.cam.ch.wwmm.oscarrecogniser.interfaces.ChemicalEntityRecogniser;
import uk.ac.cam.ch.wwmm.oscarrecogniser.saf.StandoffResolver.ResolutionMode;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntity;
import dan2097.org.bitbucket.neRecognitionEvaluator.NamedEntityProvider;

public class CombinedTaggerNameProvider implements NamedEntityProvider{

	static Oscar oscar4 = new Oscar();
	public static ChemicalEntityRecogniser recogniser = new MEMMRecogniser(new ChemPapersModel(), OntologyTerms.getDefaultInstance(), new ChemNameDictRegistry(Locale.ENGLISH));

	public List<NamedEntity> findNamedEntities(String input) {
		List<NamedEntity> chemicalEntities = new ArrayList<NamedEntity>();
		List<TokenSequence> tokSequenceList = oscar4.tokenise(input);
		List<uk.ac.cam.ch.wwmm.oscar.document.NamedEntity> oscarNeList = recogniser.findNamedEntities(tokSequenceList, ResolutionMode.MARK_BLOCKED);
		for (int i = oscarNeList.size() -1; i >=0; i--) {
			NamedEntityType type = oscarNeList.get(i).getType();
			if (!type.isInstance(NamedEntityType.COMPOUND)){
				oscarNeList.remove(i);
			}
		}
		List<String> tokenList = new ArrayList<String>();
		TokenSequence tokenSequence = tokSequenceList.get(0);
		for (Token token : tokenSequence.getTokens()) {
			tokenList.add(token.getSurface());
		}
		
		List<String> oscarAndOpsinList = new ArrayList<String>();
		for (int i = 0; i < tokenList.size(); i++) {
			oscarAndOpsinList.add("nil");
		}
		for (uk.ac.cam.ch.wwmm.oscar.document.NamedEntity ne : oscarNeList) {
			List<Token> tokens = ne.getTokens();
			for (Token token : tokens) {
				oscarAndOpsinList.set(token.getIndex(), "OSCAR-"+ne.getType().getName());
			}
		}
		List<IdentifiedChemicalName> identifiedNames = new DocumentToStructures(tokenList).extractNames();
		for (IdentifiedChemicalName ne : identifiedNames) {
			for (int i = ne.getWordPositionStartIndice(); i <= ne.getWordPositionEndIndice(); i++) {
				oscarAndOpsinList.set(i, "OSCAR-CM");
			}
		}
		
		for (int i = 0; i < oscarAndOpsinList.size(); i++) {
			String tag = oscarAndOpsinList.get(i);
			if (tag.equals("OSCAR-CM")){
				int startIndice = i;
				while (i+1 <oscarAndOpsinList.size() && oscarAndOpsinList.get(i+1).equals("OSCAR-CM") ){
					i++;
				}
				int finalIndice = i;
				String surface = tokenSequence.getSubstring(startIndice, finalIndice);
				chemicalEntities.add(new NamedEntity(surface, tokenSequence.getToken(startIndice).getStart(), "CM"));
			}
		}
		return chemicalEntities;
	}


}
