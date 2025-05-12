exports.getAllBooks = async (req, res, next) => {
    res.send("Get all books route");
};

exports.getAllPosts = async (req, res, next) => {
    res.send("Get All Posts Route");
}

exports.createNewPost = async (req, res, next) => {
    res.send("Create New Post Route");
}