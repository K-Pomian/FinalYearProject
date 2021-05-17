package finalyearproject.utils.enums;

import java.net.URL;

public enum TestType {
	UOB ("https://www.bradford.ac.uk/external/");
	
	private final String url;
	
	TestType(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
