
use test; 

create table cat(
    id          int not null, 
    name        varchar(40) not null, 
    primary key cat__pk(id), 
    index       cat__name(name)
    )
    character set utf8 collate utf8_general_ci;

create table cat_isa(
    sub         int not null, 
    sup         int not null, 
    primary key cat_isa_pk(sub, sup)
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
    
insert into cat_isa(sub, sup) values
    (4, 3), 
    (5, 3), 
    (4, 1), 
    (5, 2), 
    (20, 3), 
    (21, 3), 
    (20, 1), 
    (21, 2), 
    (3, 6), 
    (7, 6), 
    (8, 6), 
    (9, 8), 
    (11, 10), 
    (12, 13), 
    (13, 11), 
    (14, 10), 
    (15, 10), 
    (16, 14), 
    (16, 12), 
    (17, 14), 
    (17, 13), 
    (18, 12), 
    (19, 18), 
    (19, 14);
