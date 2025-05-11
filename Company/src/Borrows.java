public class Borrows {
    private String isbn;
    private int pid;
    private String borrowedDate;
    private int borrowedLength;
    private String returnDate;
    private boolean overdue;
    private String condition;

    public Borrows(String isbn, int pid, String borrowedDate, int borrowedLength,
    String returnDate, boolean overdue, String condition) {
        this.isbn = isbn;
        this.pid = pid;
        this.borrowedDate = borrowedDate;
        this.borrowedLength = borrowedLength;
        this.returnDate = returnDate;
        this.overdue = overdue;
        this.condition = condition;

    }

    public String getisbn() {
        return this.isbn;
    }

    public int getpid() {
        return this.pid;
    }

    public String getBorrowedDate() {
        return this.borrowedDate;
    }

    public void setBorrowedDate(String bd) {
        this.borrowedDate = bd;
    }

    public int getBorrowedLength() {
        return this.borrowedLength;
    }

    public void setBorrowedlegth(String bl) {
        this.borrowedDate = bl;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(String rd) {
        this.returnDate = rd;
    }

    public boolean overdue() {
        return this.overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
	public String toString() {
		return "Borrows [ISBN=" + isbn + ", PID=" + pid + ", BorrowedDate=" + borrowedDate + ", BorrowedLength =" + borrowedLength + ", ReturnDate=" + returnDate + ", Overdue=" + overdue + ", Conditions= " + condition + "]";
	}
}