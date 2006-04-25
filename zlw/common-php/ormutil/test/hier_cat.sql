
use test; 

create table cat(
    id          int not null, 
    name        varchar(40) not null, 
    primary key cat__pk(id), 
    index       cat__name(name)
    )
    character set utf8 collate utf8_general_ci;

create table isa(
    sub         int not null, 
    sup         int not null, 
    primary key isa_pk(sub, sup)
    )
    character set utf8 collate utf8_general_ci;

insert into cat(id, name) values
    (1, 'male'), 
    (2, 'female'), 
    (3, 'human'), 
    (4, 'man'), 
    (5, 'women'), 
    (6, 'animal'), 
    (7, 'dog'), 
    (8, 'cat'), 
    (9, 'tiger'), 
    (10, 'number'), 
    (11, 'real'), 
    (12, 'integer'), 
    (13, 'rational'), 
    (14, 'positive'), 
    (15, 'negative'), 
    (16, 'positive integer'), 
    (17, 'positive rational'), 
    (18, 'short integer'), 
    (19, 'short positive integer'), 
    (20, 'boy'), 
    (21, 'girl');
    
insert into isa(sub, sup) values
    (4, 3),                             -- human -> man
    (5, 3),                             --          women
    (4, 1),                             -- male  -> man
    (5, 2),                             -- female-> women
    (20, 3),                            -- human -> boy
    (21, 3),                            --          girl
    (20, 1),                            -- male  -> boy
    (21, 2),                            -- female-> girl
    (3, 6),                             -- animal-> human
    (7, 6),                             --          dog
    (8, 6),                             --          cat
    (9, 8),                             --          tiger
    (11, 10),                           -- number-> real
    (12, 13),                           -- ration-> integer
    (13, 11),                           -- real  -> rational
    (14, 10),                           -- number-> positive
    (15, 10),                           --          negative
    (16, 14),                           -- positi-> pos integer
    (16, 12),                           -- intege-> pos integer
    (17, 14),                           -- positi-> pos rational
    (17, 13),                           -- ration-> pos rational
    (18, 12),                           -- intege-> short
    (19, 18),                           -- short -> short pos integer
    (19, 14);                           -- positi-> short pos integer
