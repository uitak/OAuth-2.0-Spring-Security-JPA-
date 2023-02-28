
-- 데이터가 insert 되는 테이블의 순서도 중요.
insert into MEMBER (USER_ID, USERNAME, PASSWORD, FULLNAME) 
	values (0, 'admin', '$2a$10$D/7ZVM24fgIUZhhO4QsCAuCzsuKvLCj44OLz4jWyW5a666G5Vk89K', '관리자');
insert into AUTHORITY (AUTHORITY_ID, NAME) 
	values (0, 'ADMIN');
insert into USER_AUTHORITY (USER_AUTHORITY_ID, USER_ID, AUTHORITY_ID) 
	values (0, 0, 0);
	
