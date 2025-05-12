const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require('../../config/database');

const Book = sequelize.define('books', {
    isbn: {
        type: DataTypes.STRING,
        allowNull: false,
        primaryKey: true
    },
    bookName: {
        type: DataTypes.STRING,
        allowNull: false
    },
    rating: {
        type: DataTypes.DOUBLE,
        allowNull: false
    },
    ratingCount: {
        type: DataTypes.INTEGER,
        allowNull: false
    },
    pages: {
        type: DataTypes.INTEGER,
        allowNull: false
    },
    publishYear: {
        type: DataTypes.INTEGER,
        allowNull: false
    }},
    {
        indexes: [
            {
                unique: true,
                fields: ['isbn']
            }
        ]
    }
);

module.exports = Book;