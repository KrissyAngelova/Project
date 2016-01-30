
public enum RatingCategory {

	hot("Hot"), trending("Trending"), fresh("Fresh");
	private String displayName;

	RatingCategory(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
