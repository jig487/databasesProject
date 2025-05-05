public class Genre {
	private int gid;
    private String genreName; 
	
	public Genre(int gid, String genreName) {
		super();
        this.gid = gid;
        this.genreName = genreName;
	}

    public int getgid() {
        return this.gid;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

	@Override
	public String toString() {
		return "Genre [GID=" + gid + ", GenreName=" + genreName + "]";
	}
}