const express = require('express');
const router = express.Router();
const PostControllers = require('../app/controllers/PostControllers');

router
    .route('/')
    .get(PostControllers.getAllPosts)
    .post(PostControllers.createNewPost);
router.route("/:id").get(PostControllers.getAllBooks);

module.exports = router;