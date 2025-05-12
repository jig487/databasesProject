const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require('../../config/database');

const Genre = sequelize.define('genres', {
    gid: {
        type: DataTypes.INTEGER,
        allowNull: false,
        primaryKey: true
    },
    genreName: {
        type: DataTypes.STRING,
        allowNull: false
    }
    },
    {
        indexes: [
            {
                unique: true,
                fields: ['gid']
            }
        ]
    }
);

module.exports = Genre;