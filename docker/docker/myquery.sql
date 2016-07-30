select * from docker_layers;
select image_id, count(*) cc from docker_layers group by image_id having count(*) > 1 order by cc desc;

select distinct image from docker_layers where image_id in (
select image_id from docker_layers group by image_id having count(*) > 10
)

--and image not like '%:dependency'
select * from docker_layers where image_id = '4dcab49015d47e8f300ec33400a02cebc7b54cadd09c37e49eccbc655279da90'
select * from docker_layers where image_id = '5f70bf18a086007016e948b04aed3b82103a36bea41755b6cddfaf10ace3c6ef'

