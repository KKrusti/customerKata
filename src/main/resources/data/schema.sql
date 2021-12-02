drop table CUSTOMER_INFO if exists;
drop sequence CUSTOMER_INFO_SEQ if exists;

create table CUSTOMER_INFO
(
    ID      NUMBER not null
        constraint PK_CUSTOMER_INFO
            primary key,
    NAME    NVARCHAR2(240),
    SURNAME NVARCHAR2(240),
    STREET  NVARCHAR2(240),
    CITY    NVARCHAR2(240),
    EMAIL   NVARCHAR2(240)
);

create sequence CUSTOMER_INFO_SEQ
    start with 1
    increment by 1 cache 100;

drop table SLOGAN if exists;
drop sequence SLOGAN_SEQ if exists;

create table SLOGAN
(
    ID          NUMBER not null
        constraint PK_SLOGAN
            primary key,
    CUSTOMER_ID NVARCHAR2(240),
    SLOGAN      NVARCHAR2(240)
);

create sequence SLOGAN_SEQ
    start with 1
    increment by 1 cache 100;
