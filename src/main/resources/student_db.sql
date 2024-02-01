drop table if exists grades;
drop table if exists students;
drop table if exists courses;
drop database student_db;

create database student_db;
use student_db;

create table students (
    student_id int(11) not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    grade_id int not null
) ENGINE=InnoDB default CHARSET=utf8;

alter table students add primary key (student_id);
alter table students modify student_id int(11) not null AUTO_INCREMENT, AUTO_INCREMENT=1;

create table courses (
    course_id int(11) not null,
    name varchar(50) not null,
    grade_id int not null
) ENGINE=InnoDB default CHARSET=utf8;

alter table courses add primary key (course_id);
alter table courses modify course_id int(11) not null AUTO_INCREMENT, AUTO_INCREMENT=1;

create table grades (
    student_id int(11) not null,
    course_id int(11) not null,
    points int(11) not null,
    max_points int(11) not null,
    final_grade int(5) not null
) ENGINE=InnoDB default CHARSET=utf8;

alter table grades add primary key (student_id, course_id);