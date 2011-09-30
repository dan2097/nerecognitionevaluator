package dan2097.org.bitbucket.neRecognitionEvaluator;

public class NamedEntity {
	private final String surface;
	private final int startPosition;
	private final String annotation;

	public NamedEntity(String surface, int startPosition, String annotation) {
		this.surface = surface;
		this.startPosition = startPosition;
		this.annotation = annotation;
	}

	public String getSurface() {
		return surface;
	}

	public int getStartPosition() {
		return startPosition;
	}
	
	public String getAnnotation() {
		return annotation;
	}
}
