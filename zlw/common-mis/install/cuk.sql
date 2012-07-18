
/* .section.  com.user.kernel */

set names 'gb2312'; 

drop table com_group_role; 
drop table com_user_role; 
drop table com_role_priv; 
drop table com_role_ml; 
drop table com_role; 
drop table com_priv_isa; 
drop table com_priv_ml;
drop table com_priv; 
drop table com_member; 
drop table com_group; 
drop table com_user; 

create table com_user(
    id          int not null auto_increment,
    name        varchar(30) not null,   /* display name */
    login       int null,               /* ext, e.g. cux.login */
    info        int null,               /* ext, e.g. cux.uinfo */
    primary key pk(id),
    index       login(login),
    index       info(info)
    );

create table com_group(
    id          int not null auto_increment,
    name        varchar(30) not null,
    info        int null,               /* ext, e.g. cux.ginfo */
    primary key pk(id), 
    unique key  name(name)
    )
    character set utf8 collate utf8_general_ci;

create table com_member(
    gid         int not null,
    uid         int not null,
    primary key pk(gid, uid),
    foreign key gid(gid)
     references com_group(id) on delete cascade,
    foreign key uid(uid)
     references com_user(id) on delete cascade
    );

create table com_priv(
    id          int not null auto_increment,
    primary key pk(id)
    ); 

create table com_priv_ml(
    priv        int not null,
    lang        int not null default 0,
    name        varchar(30) not null,
    brief       varchar(200) null,
    primary key pk(priv, lang), 
    unique key  name(name),
    foreign key priv(priv)
     references com_priv(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

create table com_priv_isa(
    sub         int not null,
    sup         int not null,
    primary key pk(sub, sup),
    foreign key sub(sub)
     references com_priv(id) on delete cascade, 
    foreign key sup(sup)
     references com_priv(id) on delete cascade
    );

create table com_role(
    id          int not null auto_increment,
    primary key com_role__pk(id)
    );

create table com_role_ml(
    role        int not null,
    lang        int not null default 0,
    name        varchar(30) not null,
    breif       varchar(200) null,
    primary key pk(role, lang),
    unique key  name(name),
    foreign key role(role)
     references com_role(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

create table com_role_priv(
    role        int not null,
    priv        int not null,
    primary key pk(role, priv),
    foreign key role(role)
     references com_role(id) on delete cascade,
    foreign key priv(priv)
     references com_priv(id) on delete cascade
    );

create table com_user_role(
    uid         int not null,
    role        int not null,
    primary key pk(uid, role), 
    foreign key uid(uid)
     references com_user(id) on delete cascade,
    foreign key role(role)
     references com_role(id) on delete cascade
    );

create table com_group_role(
    gid         int not null,
    role        int not null,
    primary key pk(gid, role),
    foreign key gid(gid)
     references com_group(id) on delete cascade,
    foreign key role(role)
     references com_role(id) on delete cascade
    );

/* .section.  views */

drop view com_member_v; 

create view com_member_v as 
    select m.gid, g.name as gname, m.uid, u.name as uname
    from com_member m, com_group g, com_user u
    where m.gid = g.id and m.uid = u.id
    ; 

/* .section.  data */

insert into com_priv(id) values
    (1),                                /* PRIV_ADMIN */
    (2),                                /* PRIV_AUTH */
    (3),                                /* PRIV_SYSTEM */
    (4),                                /* PRIV_NETWORK */
    (5),                                /* PRIV_FILE */
    (6);                                /* PRIV_DATABASE */

insert into com_priv_ml(lang, priv, name, brief) values
    (0, 1, 'Administration', 'The highest privilege, include all rights. '),
    (0, 2, 'Authorization', 'Allow to authorize lower rights to other users. '),
    (0, 3, 'System', 'System control, allow access devices, install/uninstall modules, execute system maintaince, etc.'),
    (0, 4, 'Network', 'Network Control, allow access network, modify network configuration, etc. '),
    (0, 5, 'File', 'File control, allow access, modify files, etc. '),
    (0, 6, 'Database', 'Database control, allow access, modify database, etc. '), 
    (2052, 1, '����', 'ϵͳ���Ȩ�ޣ�ϵͳ����Ȩ��'),
    (2052, 2, '��Ȩ', '�������������û������������Ȩ�޵�Ȩ�ޡ�'),
    (2052, 3, 'ϵͳ', 'ϵͳ����Ȩ�ޣ�����ʹ���豸����װ/ж����չģ�顢ִ��ϵͳά���ȡ�'),
    (2052, 4, '����', '�������Ȩ�ޣ�����������硢�����������õȡ�'),
    (2052, 5, '�ļ�', '�ļ�����Ȩ�ޣ�������ʡ������ļ��ȡ�'),
    (2052, 6, '���ݿ�', '���ݿ����Ȩ�ޣ�������ʡ��������ݿ�ȡ�');

insert into com_priv_isa(sup, sub) values
    (1, 2),                             /* ADMIN: AUTH */
    (1, 3),                             /* ADMIN: SYSTEM */
    (1, 4),                             /* ADMIN: NETWORK */
    (1, 5),                             /* ADMIN: FILE */
    (1, 6);                             /* ADMIN: DATABASE */

insert into com_role(id) values
    (1),                                /* ROLE_ADMIN */
    (2),                                /* ROLE_OPERATOR (system user) */
    (3),                                /* ROLE_USER (registered user) */
    (4);                                /* ROLE_GUEST */

insert into com_role_ml(lang, role, name, breif) values
    (0, 1, 'Admin', 'Administrator has all rights to configure, or set up everything in the system.  However, administrator may not be able to use the business functions directly. '),
    (0, 2, 'Operator', 'Operates the system, processes user requests, and manages some parts of the system which is related to the operation. '),
    (0, 3, 'User', 'Registered user. '),
    (0, 4, 'Guest', 'Non-registered user. '), 
    (2052, 1, '����Ա', '����Ա���Թ�������ϵͳ���ã�������ϵͳ�йص�����ѡ���������ֱ�Ӳ�����ҵ���йص����ˡ�'),
    (2052, 2, '����Ա', '����Ա��ϵͳ�Ĳ����ߣ������û����󣬲�����ϵͳ���������صĲ��֡�'),
    (2052, 3, 'ע���û�', 'ע���û����Ѿ������ʻ���Ϣ��Զ���û���'),
    (2052, 4, '�����û�', '�����û���δע���û���');

insert into com_role_priv(role, priv) values
    (1, 1),                             /* Admin -> Admin */
    (2, 4),                             /* Operator -> Network */
    (2, 5),                             /* Operator -> File */
    (2, 6),                             /* Operator -> Database */
    (3, 6);                             /* User -> Database */

insert into com_group(id, name) values
    (1, 'Administrators'),
    (2, 'Operators'),
    (3, 'Users'),
    (4, 'Guests'); 

insert into com_group_role(gid, role) values
    (1, 1),                             /* Administrators -> Admin */
    (2, 2),                             /* Operators -> Operator */
    (3, 3),                             /* Users -> User */
    (4, 4);                             /* Guests -> Guest */

insert into com_user(id, name) values
    (1, 'root'),
    (2, 'test'),                        /* test operator */
    (3, 'test1'),                       /* test user 1 */
    (4, 'test2'),                       /* test user 2 */
    (5, 'guest'); 

insert into com_user_role(uid, role) values
    (4, 2);                             /* test user 2 is also an operator */

insert into com_member(gid, uid) values
    (1, 1),                             /* Administrators -> root */
    (2, 2),                             /* Operators -> operator test */
    (3, 3),                             /* Users -> user test1 */
    (3, 4),                             /* Users -> user test2 */
    (4, 5);                             /* Guests -> guest */

