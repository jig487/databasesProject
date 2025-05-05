public class Book {
	private String isbn;
	private String bookName;
	private Double rating;
	private int ratingCount;
	private int pages;
	private int publishYear;
	
	public Book(String isbn, String bookName, Double rating, int ratingCount, int pages, int publishYear) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.pages = pages;
		this.publishYear = publishYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPublishYear() {
		return this.publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + isbn + ", BookName=" + bookName + ", Rating=" + rating + ", RatingCount=" + ratingCount
				+ ", Pages=" + pages + ", PublishYear=" + publishYear + "]";
	}
}