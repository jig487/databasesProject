public class Wrote {
    private int isbn;
    private int aid;

    public Wrote(int isbn, int aid) {
        this.isbn = isbn;
        this.aid = aid;
    }

    public int getisbn() {
        return this.isbn;
    }

    public int getaid() {
        return this.aid;
    }

    @Override
	public String toString() {
		return "Wrote [ISBN=" + isbn + ", AID=" + aid + "]";
	}
}
