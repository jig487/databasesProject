public class Borrows {
    private int isbn;
    private int pid;

    public Borrows(int isbn, int pid) {
        this.isbn = isbn;
        this.pid = pid;
    }

    public int getisbn() {
        return this.isbn;
    }

    public int getpid() {
        return this.pid;
    }

    @Override
	public String toString() {
		return "Borrows [ISBN=" + isbn + ", AID=" + pid + "]";
	}
}