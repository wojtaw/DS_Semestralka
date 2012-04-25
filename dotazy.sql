/* tento select přidá k id presentací všechny jejich tagy */

select presentations.id as presentation_id, array(select tags.name from tags,tags_presentations where tags.id = tags_presentations.tag_id and presentations.id = tags_presentations.presentation_id) as tag_array from presentations order by id;
