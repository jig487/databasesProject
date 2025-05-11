public class Purchase {
    private String isbn;
    private int oid;
    private String purchaseDate;
    private float purchasePrice;

    public Purchase(String isbn, int oid, String purchaseDate, float purchasePrice) {
        this.isbn = isbn;
        this.oid = oid;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

    public String getisbn() {
        return this.isbn;
    }

    public int getoid() {
        return this.oid;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(String pd) {
        this.purchaseDate = pd;
    }

    public float getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(float pp) {
        this.purchasePrice = pp;
    }

    @Override
	public String toString() {
		return "Owns [ISBN=" + isbn + ", OID=" + oid + ", PurchaseDate=" + purchaseDate + ", PurchasePrice=" + purchasePrice + "]";
	}
}