INSERT INTO user_table(email,first_name,last_name,"password",user_name) VALUES ('email','firstname','lastname','password','username');
INSERT INTO user_table(email,first_name,last_name,"password",user_name) VALUES ('email2','firstname2','lastname2','password2','username2');

INSERT INTO post_table (post_content,date_created ,user_fk) VALUES ('Test Post 1', CURRENT_DATE, 1);
INSERT INTO post_table (post_content,date_created ,user_fk) VALUES ('Test Post 2', CURRENT_DATE, 2);

--SELECT * FROM comment_table ct ;
--SELECT * FROM user_table ut ;
SELECT * FROM post_table pt ;
SELECT * FROM post_table_likers ptl ;