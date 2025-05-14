const Post = require("../models/Post");

exports.getAllPosts = async (req, res, next) => {
  try {
    const [posts, _] = await Post.findAll();

    res.status(200).json({ count: posts.length, posts });
  } catch (error) {
    console.log(error);
    next(error);
  }
};

exports.createNewPost = async (req, res, next) => {
  try {
    let { ISBN, BookName, Rating, RatingCount, Pages, PublishYear } = req.body;
    let post = new Post( ISBN, BookName, Rating, RatingCount, Pages, PublishYear );

    post = await post.save();

    res.status(201).json({ message: "Book created successfully", post});
  } catch (error) {
    console.log(error);
    next(error);
  }
};

exports.getPostByISBN = async (req, res, next) => {
  try {
    let ISBN = req.params.ISBN;
    let [post, _] = await Post.findByISBN(ISBN);

    res.status(200).json({ post: post[0] });
  } catch (error) {
    console.log(error);
    next(error);
  }
};

exports.getPostByBookName = async (req, res, next) => {
  try {
    let BookName = req.params.BookName;
    let [post, _] = await Post.findByBookName(BookName);

    res.status(200).json({ post: post[0] });
  } catch (error) {
    console.log(error);
    next(error);
  }
};

exports.getPostByRating = async (req, res, next) => {
  try {
    let Rating = req.params.Rating;
    let [post, _] = await Post.findByRating(Rating);

    res.status(200).json({ post: post[0] });
  } catch (error) {
    console.log(error);
    next(error);
  }
};

exports.getPostByPublishYear = async (req, res, next) => {
  try {
    let PublishYear = req.params.PublishYear;
    let [post, _] = await Post.findByPublishYear(PublishYear);

    res.status(200).json({ post: post[0] });
  } catch (error) {
    console.log(error);
    next(error);
  }
};