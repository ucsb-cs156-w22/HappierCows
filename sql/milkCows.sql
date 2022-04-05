INSERT INTO User (wealth, type, createdAt, updatedAt, CommonId, UserId)
    SELECT
        (SELECT milkPrice FROM Configs AS conf WHERE conf.CommonId = cw.CommonId) *
        (1-(SELECT tax FROM TieredTaxings AS t INNER JOIN
            Configs AS c ON c.Id = t.ConfigId WHERE c.CommonId = cw.CommonId) / 100)
        AS wealth,
        "milk" AS `status`,
        CURRENT_TIMESTAMP AS createdAt,
        CURRENT_TIMESTAMP AS updatedAt,
        CommonId,
        UserId FROM Cows AS cw

-- for each user update user's totalWeath += milkPrice * (cows * cowHealth)