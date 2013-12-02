/*historial de conexiones a administrador*/
select det_his, fec_his from historial
where fec_his between "2011-09-26 0:0:0" 
and "2012-09-26 23:59:59" and 
nom_tab = 'administrador' and 
cod_per is null order by fec_his desc;