
/* .section.  com.app.module */

set names 'gb2312';

drop table com_menu_of;
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

create table com_menu_of(
    parent      int not null,
    child       int not null,
    pr          int not null,           /* precedence (order) */
    primary key pk(parent, child),
    foreign key parent(parent)
     references com_menu(id) on delete cascade,
    foreign key child(child)
     references com_menu(id) on delete cascade, 
    index       pr(pr)
    );
