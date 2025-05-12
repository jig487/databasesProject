const express = require('express');
const router = express.Router();
const CrudControllers = require('../app/controllers/CrudControllers');

router
    .route('/')
    .get(CrudControllers.getAllPosts)
    .post(CrudControllers.createNewPost);
router.route("/:id").get(CrudControllers.getAllBooks);