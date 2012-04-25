/* tento select přidá k id presentací všechny jejich tagy */

select presentations.id as presentation_id, array(select tags.name from tags,tags_presentations where tags.id = tags_presentations.tag_id and presentations.id = tags_presentations.presentation_id order by tags.id) as tag_array from presentations order by id;

/* tento select vrátí k presentaci 6 id presentací, které mají shodné tagy a jejich počet. Na 1. řádku je sama presentace s počtem tagů. */
select nested.presentation_id as np ,count(tp.tag_id) as eqtags from tags_presentations tp, (select * from tags_presentations) as nested where tp.presentation_id=6 and tp.tag_id=nested.tag_id group by np order by eqtags desc;

select sy.loads_count- mean_loads_y, sx.loads_count-mean_plays_x,sy.loads_count,mean_loads_y,sx.loads_count,mean_loads_x
from 
statistics sy,
statistics sx,
(select
	coalesce(sum(ssx.loads_count)/count(ssx.loads_count)::float,.0) as mean_loads_x,
	coalesce(sum(ssy.loads_count)/count(ssy.loads_count)::float,.0) as mean_loads_y,
	coalesce(sum(ssx.plays_count)/count(ssx.plays_count)::float,.0) as mean_plays_x,
	coalesce(sum(ssy.plays_count)/count(ssy.plays_count)::float,.0) as mean_plays_y,
	coalesce(sum(ssx.finishes_count)/count(ssx.finishes_count)::float ,.0)as mean_finishes_x,
	coalesce(sum(ssy.finishes_count)/count(ssy.finishes_count)::float ,.0)as mean_finishes_y
	from (select * from statistics where presentation_id=245) as ssx, (select * from statistics where presentation_id=34) as ssy) as means
 where sx.presentation_id=245 and sy.presentation_id=34 ;

select s.presentation_id, s.user_id, coalesce(s1.plays_count,0) as plays_x, coalesce(s2.plays_count,0) as plays_y  from statistics s left outer join statistics s1 on (s.user_id = s1.user_id and s1.presentation_id=245) left outer join statistics s2 on (s.user_id = s2.user_id and s2.presentation_id=34) where s.presentation_id in(245,34) order by user;
