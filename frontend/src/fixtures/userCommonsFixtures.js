const userCommonsFixtures = {
    oneUserCommons: 
    [
        {
            "id":1,
            "user": {
                "id": 1
            },
            "commons":  {
                "id":1,
                "name":"Anika's cows",
                "day": 5,
                "endDate":"6/11/2021",
                "totalPlayers": 50,
                "cowPrice": 15,
            },
            "totalWealth" : 1000,
            "cowHealth": 98.0,
            "profits" : 
            [{ id: 1, profit: 10, date: "2021-03-05" },
            { id: 2, profit: 11, date: "2021-03-06" },
            { id: 3, profit: 10, date: "2021-03-07" },
            { id: 4, profit: 8, date: "2021-03-08" }],
        }
    ]
}

export default userCommonsFixtures;