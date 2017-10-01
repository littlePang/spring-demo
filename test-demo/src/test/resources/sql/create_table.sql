create table user_info (
id bigint(20) auto_increment comment '主键id',
name varchar(20) not null default '' comment '名字',
password varchar(30) not null default '' comment '密码',
primary key (id)
)engine=innodb default charset=utf8;