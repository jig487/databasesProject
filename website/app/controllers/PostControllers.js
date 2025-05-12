const Post = require('../models/Post');

exports.getAllBooks = async (req, res, next) => {
    res.send("Get all books route");
};

exports.getAllPosts = async (req, res, next) => {
    res.send("Get All Posts Route");
}

exports.createNewPost = async (req, res, next) => {
    let { isbn, bookName, rating, ratingCount, pages, publishYear } = req.body;
    const post = new Post(isbn, bookName, rating, ratingCount, pages, publishYear);

    post = await post.save();

    console.log(post);

    res.send("Create New Post Route");

    /* Postman test json:
    {
    "isbn": 1, 
    "bookName": "TEST First Post Name", 
    "rating": 1.0, 
    "ratingCount": 1, 
    "pages": 1, 
    "publishYear": 11111111
    }
    */
}