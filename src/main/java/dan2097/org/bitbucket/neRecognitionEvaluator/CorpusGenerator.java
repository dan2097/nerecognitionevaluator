package dan2097.org.bitbucket.neRecognitionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CorpusGenerator {

	public static List<Corpus> generateCorpora(){
		try{
			InputStream is = CorpusGenerator.class.getResourceAsStream("chemicals-test-corpus-27-04-2009-v3.iob");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			List<Corpus> corpora = new ArrayList<Corpus>();
			while ((line = br.readLine())!=null && line.startsWith("###")){
				corpora.add(extractCorpus(br));
			}
			return corpora;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Corpus extractCorpus(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		int lastEndingIndice = 0;
		List<NamedEntity> expectedEntities = new ArrayList<NamedEntity>();
		while ((line = br.readLine())!=null){
			if (line.length()==0){
				break;
			}
			String[] lineContents = line.split("\t");
			String surface = lineContents[0];
			int startPosition = Integer.parseInt(lineContents[1]);
			int endPosition = Integer.parseInt(lineContents[2]);
			String namedEntitySurface = lineContents[3];
			String annotation = lineContents[4].substring(1);
			if (!namedEntitySurface.equals("")){
				if (entityTypeIsOutOfScope(annotation)){
					continue;
				}
				expectedEntities.add(new NamedEntity(namedEntitySurface, startPosition, annotation));
			}
			for (int i = lastEndingIndice; i < startPosition; i++) {
				sb.append(" ");
			}
			sb.append(surface);
			lastEndingIndice = endPosition;
		}
		String sentence = sb.toString();
		return new Corpus(sentence, expectedEntities);
	}
	
	private static boolean entityTypeIsOutOfScope(String annotation) {
		return !annotation.equals("B-IUPAC") && !annotation.equals("B-PARTIUPAC") && !annotation.equals("B-FAMILY") &&
				!annotation.equals("B-SUM") && !annotation.equals("B-TRIVIAL") && !annotation.equals("B-ABBREVIATION");
	}
}
