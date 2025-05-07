public class Author {
    private int aid;
    private String authorName; 
	
	public Author(int aid, String authorName) {
		super();
        this.aid = aid;
        this.authorName = authorName;
	}

    public int getaid() {
        return this.aid;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

	@Override
	public String toString() {
		return "Author [AID=" + aid + ", AuthorName=" + authorName + "]";
	}
}
