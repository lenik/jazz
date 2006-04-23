
/* .section.  com.app.module */

set names 'gb2312';

drop table com_mod_menu;
drop table com_mod_access;
drop table com_mod_files;
drop table com_mod_inst;
drop table com_mod_prereq; 
drop table com_mod; 
drop table com_menu_isa;
drop table com_menu;

create table com_menu(
    id          int not null auto_increment,
    title       varchar(100) not null,
    icon        varchar(200) null,
    tooltip     varchar(200) null,
    primary key pk(id),
    index       title(title)
    )
    character set utf8 collate utf8_general_ci;

create table com_menu_isa(
    parent      int not null,
    child       int not null,
    pr          int not null,
    primary key pk(parent, child),
    foreign key parent(parent)
     references com_menu(id) on delete cascade,
    foreign key child(child)
     references com_menu(id) on delete cascade, 
    index       pr(pr)
    );

create table com_mod(
    id          int not null auto_increment,
    name        varchar(30) not null,
    cat         int null,               /* category */
    brief       varchar(200) null,
    icon        varchar(200) null,      /* small icon */
    author      varchar(50) null,
    email       varchar(30) null,
    ver         varchar(30) null,
    primary key pk(id),
    unique key  name(name),
    index       cat(cat),
    index       author(author),
    index       ver(ver)
    )
    character set utf8 collate utf8_general_ci;

create table com_mod_prereq(            /* isa-compat */
    m           int not null,
    req         int not null,
    primary key pk(m, req),
    foreign key m(m)
     references com_mod(id) on delete cascade,
    foreign key req(req)
     references com_mod(id) on delete restrict
    );

create table com_mod_inst(
    m           int not null, 
    instbase    varchar(200) not null,  /* install base directory */
    entry       varchar(200) not null,  /* entry-point, relative to instbase */
    primary key pk(m),
    foreign key m(m)
     references com_mod(id) on delete restrict,
    index       instbase(instbase),
    index       entry(entry)
    )
    character set utf8 collate utf8_general_ci;

create table com_mod_files(
    id          int not null auto_increment,
    m           int not null,
    path        varchar(200) not null,  /* relative to instbase */
    primary key pk(id),
    foreign key m(m)
     references com_mod(id) on delete cascade, 
    index       m(m),
    index       path(path)
    )
    character set utf8 collate utf8_general_ci;

create table com_mod_access(
    m           int not null,
    priv        int not null,
    primary key pk(m, priv),
    foreign key m(m)
     references com_mod(id) on delete cascade,
    foreign key priv(priv)
     references com_priv(id) on delete cascade
    );

create table com_mod_menu(
    m           int not null, 
    menu        int not null,
    primary key pk(m, menu),
    foreign key m(m)
     references com_mod(id) on delete cascade,
    foreign key menu(menu)
     references com_menu(id) on delete cascade
    );

