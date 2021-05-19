package finalyearproject.utils.enums;

public enum TestType {
	UOB ("https://www.bradford.ac.uk/external/"),
	Evision ("https://evision.brad.ac.uk/");
	
	private final String url;
	
	TestType(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
