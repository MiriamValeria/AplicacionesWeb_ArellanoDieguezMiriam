//Esto es un import
const express = require('express');
const app = express();

const PORT = 3000;

const games = [

    {id: 1, name: "Amoung Us", genre: "Strategy" },
    {id: 2, name: "Clash Royale", genre: "Tower Defense" },
    {id: 3, name: "Minecraft", genre: "Adventure" },
    {id: 4, name: "Fornite", genre: "Battle Royale" }

];

app.get("/games", (req, res) => {
    res.json(games);
});

app.get("/games2", (req, res) => {
    res.json(games);
});

app.listen(PORT, () => { //Arrow function
    console.log(`App is running on port  "+PORT);`);
});