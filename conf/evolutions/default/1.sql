# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbDepartment;

drop table tbFaculty;

drop table tbStudent;

drop table tbUser;

SET FOREIGN_KEY_CHECKS=1;

