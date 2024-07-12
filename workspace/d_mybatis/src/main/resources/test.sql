create table table_name
(
    id     NUMBER
        constraint pk_bird
            primary key,
    name   varchar2(100),
    age    NUMBER,
    gender varchar2(100)
);

CREATE SEQUENCE SEQ_BIRD
START WITH 1
INCREMENT BY 1
NOCACHE ;

SELECT * FROM TBL_BIRD;

SELECT SEQ_BIRD.nextval FROM DUAL;

select * from TBL_BIRD
where ID = 1;



