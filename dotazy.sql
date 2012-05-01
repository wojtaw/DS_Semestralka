/* Tento select vrátí k presentaci $1 id presentací, které s ní mají nějaké shodné tagy a jejich počet. */
PREPARE similar_tags(integer) AS
SELECT nested.presentation_id AS np ,count(tp.tag_id) AS eqtags
FROM
	tags_presentations tp,
	(SELECT * FROM tags_presentations) AS nested
WHERE tp.presentation_id=$1 AND tp.tag_id=nested.tag_id
GROUP BY np
ORDER BY eqtags DESC
OFFSET 1;


/* Tento select najde $2 nejpodobnějších prezentací k prezentaci $1
ve smyslu korelace počtu shlédnutí přes uživatele */
PREPARE similar_corr (integer, integer) AS 
SELECT
	c1.p_id AS presentation_id,
	COALESCE(corr(c1.plays_count, COALESCE(c2.plays_count,0.0)),-1.0) AS correlation
FROM 
	(SELECT p.id AS p_id, user_id, plays_count
		FROM statistics, presentations p
		WHERE presentation_id=$1 ORDER BY user_id
	) AS c1 LEFT JOIN
		(SELECT s2.user_id, s2.presentation_id AS p_id, s2.plays_count AS plays_count
				FROM statistics s1 LEFT JOIN statistics s2 ON (s1.user_id = s2.user_id)
				WHERE s1.presentation_id=$1
				ORDER BY s1.user_id
		) AS c2
	ON (c1.user_id=c2.user_id AND c1.p_id=c2.p_id)
GROUP BY c1.p_id
ORDER BY correlation DESC
LIMIT $2 OFFSET 1;
