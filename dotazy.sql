/* tento select přidá k id presentací všechny jejich tagy */

select presentations.id as presentation_id, array(select tags.name from tags,tags_presentations where tags.id = tags_presentations.tag_id and presentations.id = tags_presentations.presentation_id order by tags.id) as tag_array from presentations order by id;

/* tento select vrátí k presentaci 6 id presentací, které mají shodné tagy a jejich počet. Na 1. řádku je sama presentace s počtem tagů. */
select nested.presentation_id as np ,count(tp.tag_id) as eqtags from tags_presentations tp, (select * from tags_presentations) as nested where tp.presentation_id=6 and tp.tag_id=nested.tag_id group by np order by eqtags desc;
