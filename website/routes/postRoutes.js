const express = require("express");
const postControllers = require("../controllers/postControllers");
const router = express.Router();

// @route GET && POST - /posts/
router
  .route("/")
  .get(postControllers.getAllPosts)
  .post(postControllers.createNewPost);

router.route("/ISBN/:ISBN").get(postControllers.getPostByISBN);
router.route("/BookName/:BookName").get(postControllers.getPostByBookName);
router.route("/Rating/:Rating").get(postControllers.getPostByRating);
router.route("/PublishYear/:PublishYear").get(postControllers.getPostByPublishYear);

module.exports = router;
