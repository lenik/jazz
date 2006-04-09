
/* com.user.ext (sample package) */

set names 'gb2312';

drop table com_ginfo;
drop table com_uinfo_contact;
drop table com_uinfo;
drop table com_contact;
drop table com_login;
drop table com_email;
drop table com_question_ml;
drop table com_question;


create table com_question(
    id          int not null auto_increment,
    primary key pk(id)
    );

create table com_question_ml(
    id          int not null,
    lang        int not null default 0, 
    text        varchar(100) not null,
    primary key pk(id, lang), 
    foreign key id(id)
     references com_question(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

create table com_email(
    id          int not null auto_increment,
    uid         int not null,           /* foreign-key skipped */
    email       varchar(30) not null, 
    flags       int not null,           /* VERIFIED, MAJOR, SENDNEWS, etc */
    primary key pk(id), 
    unique key  email(uid, email),
    index       flags(flags)
    )
    character set utf8 collate utf8_general_ci;

create table com_login(
    id          int not null auto_increment, 
    name        varchar(30) not null,
    code        char(16) null,          /* something like barcode, etc */
    pwd         char(40) not null,      /* SHA-1, 160-bit */
    q           int null,               /* password question */
    a           varchar(200) null,      /* password answer */
    email       int null,               /* email for password reset */
    state       int not null default 0, /* ONLINE, N/A, HAVEMAIL, HAVEMSG, etc */
    primary key pk(id),
    unique key  name(name),
    index       code(code), 
    index       email(email),
    foreign key email(email)
     references com_email(id) on delete set null,
    foreign key q(q)
     references com_question(id) on delete set null
    )
    character set utf8 collate utf8_general_ci;

create table com_contact(
    id          int not null auto_increment,
    cn          int not null,           /* see cdw.country */
    sp          varchar(30) not null,   /* state/province */
    city        varchar(30) not null,
    addr        varchar(100) not null,
    post        varchar(20) null,
    tel         varchar(30) null,       /* telephone */
    cell        varchar(30) null,       /* cellphone */
    primary key pk(id),
    index       loc(cn, sp, city, addr),
    index       tel(tel),
    index       cell(cell)
    )
    character set utf8 collate utf8_general_ci;

create table com_uinfo(
    id          int not null auto_increment,
    first       varchar(30) not null,   /* first name */
    last        varchar(30) not null,   /* last name */
    mc          int null,               /* main (active) contact */
    icon        int null,               /* icon image */
    primary key pk(id),
    index       first(first),
    index       last(last),
    foreign key mc(mc)
     references com_contact(id) on delete set null
    )
    character set utf8 collate utf8_general_ci;

create table com_uinfo_contact(
    uinfo       int not null,
    contact     int not null,
    primary key pk(uinfo, contact),
    foreign key uinfo(uinfo)
     references com_uinfo(id) on delete cascade,
    foreign key contact(contact)
     references com_contact(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

create table com_ginfo(
    id          int not null auto_increment,
    brief       varchar(200) not null, 
    icon        int null,
    primary key pk(id)
    )
    character set utf8 collate utf8_general_ci;

/* .section.  data */

insert into com_question(id) values
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10); 

insert into com_question_ml(lang, id, text) values
    (0, 1, 'What is the name of your pet? '),
    (0, 2, 'What is the second word of the name of your first love? '),
    (0, 3, 'What is the name of your most favorite movie? '),
    (0, 4, 'In which place happened the most indelible day to you? '),
    (0, 5, 'Whom do your respect most? '),
    (0, 6, 'Whom do you despise most? '),
    (0, 7, 'What do you wanna receive when in your birthday? '),
    (0, 8, 'When is your mother\'s birthday? '),
    (0, 9, 'What is your social protection cde? '),
    (0, 10, 'What is your driving license code? '),
    (2052, 1, '你的宠物叫什么名字？'),
    (2052, 2, '你的初恋情人名字的第二个字？'),
    (2052, 3, '你最喜欢的电影名称？'),
    (2052, 4, '你最难忘的一天发生在什么地方？'),
    (2052, 5, '你最敬仰的人物？'),
    (2052, 6, '你最鄙视的人物'),
    (2052, 7, '你生日的时候想得到什么礼物？'),
    (2052, 8, '你母亲的生日？'),
    (2052, 9, '你的身份证号码？'),
    (2052, 10, '你的驾照号码？');

insert into com_email(id, uid, email, flags) values
    (1, 1, 'root@localhost', 1),        /* root[0], verified */
    (2, 2, 'operator@localhost', 1),    /* test[0], verified */
    (3, 4, 'test@localhost', 1);        /* test2[0], non-verified */

insert into com_login(id, name, code, q, a, email, pwd) values
    (1, 'root', '00001007', 3, '007', 1,
     '55bc8ce77b836b78df75027dcc2777683e13c153'), /* 00001007 */
    (2, 'root-bak', null, 3, 'micky mouse', 1,
     'b3ae2d37ca6cb225c0c2f3b95b8edd336d651008'), /* ZeRo */
    (3, 'test-op', '00001014', 3, 'no movie', 2,
     'b0352f5c6792f519bc68d9ef8f6c8af58cb28c26'), /* 00001014 */
    (4, 'test1', '00001021', 3, 'bruce lee', null,
     '8c71c30c9715e48fd493e281ebafb2fb5bae6533'), /* 00001021 */
    (5, 'test2', '00001038', 3, 'sex show', 3,
     '4a70f82e0bf731f6f804db0cb79234289da22f03'); /* 00001038 */

insert into com_contact(id, cn, sp, city, addr, post, tel, cell) values
    (1, 1, 'Zhejiang', 'Yuhuan', '301 Room, 13 Chengdong Rd.', '317605', null, null),
    (2, 1, 'Zhejiang', 'Haining', '603 Room, 297 Changdai Rd.', '314400', null, '13567385103'),
    (3, 1, 'Tibet', 'Lhasa', '239 New bright Rd.', '249284', '23-983-3931', '13800571505'); 

insert into com_uinfo(id, first, last, mc) values
    (1, 'Xima', 'Lenik', 2),            /* root */
    (2, 'Cao', 'Zuoyuan', null),        /* operator test */
    (3, 'Yong', 'Huyi', null),          /* user test1 */
    (4, 'Dier', 'Yonghu', 3);           /* user test2 */

insert into com_uinfo_contact(uinfo, contact) values
    (1, 1),                             /* root[0] */
    (1, 2),                             /* root[1] */
    (4, 3);                             /* test2[0] */
