const db = require("../config/db");

class Post {
  constructor(ISBN, BookName, Rating, RatingCount, Pages, PublishYear) {
    this.ISBN = ISBN;
	this.BookName = BookName;
	this.Rating = Rating;
	this.RatingCount = RatingCount;
	this.Pages = Pages;
	this.PublishYear = PublishYear;
  }

  save() {
    let sql = `
    INSERT INTO Book(
        ISBN,
        BookName,
        Rating,
        RatingCount,
        Pages,
        PublishYear
    )
    VALUES(
        ${this.ISBN},
	    '${this.BookName}',
	    ${this.Rating},
	    ${this.RatingCount},
	    ${this.Pages},
	    '${this.PublishYear}'
    )
    `;

    return db.execute(sql);
  }

  static findAll() {
    let sql = "SELECT * FROM Book;";

    return db.execute(sql);
  }

  static findByISBN(ISBN) {
    let sql = `SELECT * FROM Book WHERE ISBN = ${ISBN};`;

    return db.execute(sql);
  }

  static findByBookName(BookName) {
    let sql = `SELECT * FROM Book WHERE BookName = ${BookName};`;

    return db.execute(sql);
  }

  static findByRating(Rating) {
    let sql = `SELECT * FROM Book WHERE Rating = ${Rating};`;

    return db.execute(sql);
  }

  static findByPublishYear(PublishYear) {
    let sql = `SELECT * FROM Book WHERE PublishYear = ${PublishYear};`;

    return db.execute(sql);
  }
}

module.exports = Post;
