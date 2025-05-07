public class IsGenre {
    private int isbn;
    private int gid;

    public IsGenre(int isbn, int gid) {
        this.isbn = isbn;
        this.gid = gid;
    }

    public int getisbn() {
        return this.isbn;
    }

    public int getgid() {
        return this.gid;
    }

    @Override
	public String toString() {
		return "IsGenre [ISBN=" + isbn + ", GID=" + gid + "]";
	}
}