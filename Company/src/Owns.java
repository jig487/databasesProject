public class Owns {
    private int isbn;
    private int oid;

    public Owns(int isbn, int oid) {
        this.isbn = isbn;
        this.oid = oid;
    }

    public int getisbn() {
        return this.isbn;
    }

    public int getoid() {
        return this.oid;
    }

    @Override
	public String toString() {
		return "Owns [ISBN=" + isbn + ", OID=" + oid + "]";
	}
}