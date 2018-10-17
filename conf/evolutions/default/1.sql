# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbBook (
  id                        varchar(255) not null,
  title                     varchar(255),
  author                    varchar(255),
  pages                     integer,
  price                     double,
  constraint pk_tbBook primary key (id))
;

create table tbDepartment (
  id                        varchar(255) not null,
  th_name                   varchar(255),
  eng_name                  varchar(255),
  level                     varchar(255),
  year                      integer,
  faculty_id                varchar(255),
  constraint pk_tbDepartment primary key (id))
;

create table tbFaculty (
  id                        varchar(255) not null,
  name                      varchar(255),
  shot_name                 varchar(255),
  constraint pk_tbFaculty primary key (id))
;

create table tbOrders (
  id                        varchar(255) not null,
  date                      datetime,
  user_id                   varchar(255),
  status                    varchar(255),
  constraint pk_tbOrders primary key (id))
;

create table tbOrdersDetail (
  id                        varchar(255) not null,
  orders_id                 varchar(255),
  book_id                   varchar(255),
  amount                    integer,
  constraint pk_tbOrdersDetail primary key (id))
;

create table tbStudent (
  id                        varchar(255) not null,
  name                      varchar(255),
  surname                   varchar(255),
  major                     varchar(255),
  level                     integer,
  constraint pk_tbStudent primary key (id))
;

create table tbUser (
  id                        varchar(255) not null,
  name                      varchar(255),
  status                    varchar(255),
  password                  varchar(255),
  constraint pk_tbUser primary key (id))
;

alter table tbDepartment add constraint fk_tbDepartment_faculty_1 foreign key (faculty_id) references tbFaculty (id) on delete restrict on update restrict;
create index ix_tbDepartment_faculty_1 on tbDepartment (faculty_id);
alter table tbOrders add constraint fk_tbOrders_user_2 foreign key (user_id) references tbUser (id) on delete restrict on update restrict;
create index ix_tbOrders_user_2 on tbOrders (user_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_orders_3 foreign key (orders_id) references tbOrders (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_orders_3 on tbOrdersDetail (orders_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_book_4 foreign key (book_id) references tbBook (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_book_4 on tbOrdersDetail (book_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbBook;

drop table tbDepartment;

drop table tbFaculty;

drop table tbOrders;

drop table tbOrdersDetail;

drop table tbStudent;

drop table tbUser;

SET FOREIGN_KEY_CHECKS=1;

