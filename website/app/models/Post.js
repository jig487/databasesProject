const db = require('../../config/db');

class Post {
    constructor(isbn, bookName, rating, ratingCount, pages, publishYear) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.pages = pages;
        this.publishYear = publishYear;
    }

    async save() {
        let sql = `
        INSERT INTO Book(
            isbn,
            bookName,
            rating,
            ratingCount,
            pages,
            publishYear
        )
        VALUES(
            '${this.isbn}',
            '${this.bookName}',
            '${this.rating}',
            '${this.ratingCount}',
            '${this.pages}',
            '${this.publishYear}'
        )`;

        const [newPost, _] = await db.execute(sql);

        return newPost;
    }

    static findAll() {

    }
}

module.exports = Post;