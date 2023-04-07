alter table User_data_followers 
add constraint FK_f9ivk719aqb0rqd8mbjhdbj 
foreign key (User_data_id) 
references User_data;

alter table User_data_following
add constraint FK_f9ivk719aqb0rqd8my0jjwjw2 
foreign key (User_data_id) 
references User_data;

alter table User_data_post
add constraint FK_f9ivk719aqb0rqd8my0jnjccn 
foreign key (User_data_id) 
references User_data;

