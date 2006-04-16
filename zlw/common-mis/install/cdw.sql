
/* .section.  com.dd.world */
/* (data dictrionary) */

set names 'gb2312'; 

drop table com_country_ml;
drop table com_country;
drop table com_continent_ml; 
drop table com_continent; 
drop table com_lang_pref;
drop table com_lang; 

create table com_lang(
    id          int not null, 
    code        char(5) not null,       /* zh_CN */
    name_en     varchar(32) not null,   /* Chinese */
    loc_en      varchar(32) not null,   /* Mainland */
    sample      varchar(32) not null,   /* 中文 */
    primary key pk(id), 
    unique key  code(code), 
    index       name(name_en), 
    index       locale(loc_en)
    )
    character set utf8 collate utf8_general_ci;

create table com_lang_pref(
    lang        int not null, 
    pr          int not null,           /* precedence */
    primary key pk(lang), 
    index       pr(pr),
    foreign key lang(lang)
     references com_lang(id) on delete cascade
    ); 

create table com_continent(
    id          int not null,
    primary key pk(id)
    ); 

create table com_continent_ml(
    continent   int not null,
    lang        int not null default 0,
    name        varchar(20) not null,
    primary key pk(continent, lang),
    index       name(name),
    foreign key continent(continent)
     references com_continent(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

create table com_country(
    id          int not null auto_increment,
    continent   int not null,
    name_en     varchar(30) not null,
    tz          int not null,           /* time zone (min) */
    size        int not null,           /* km^2 */
    telp        int not null,           /* tel prefix */
    flag        int null,               /* image */
    primary key pk(id),
    index       continent(continent), 
    index       name(name_en),
    index       tz(tz),
    index       size(size), 
    index       telp(telp)
    )
    character set utf8 collate utf8_general_ci;

create table com_country_ml(
    cn          int not null,
    lang        int not null default 0,
    name        varchar(30) not null, 
    capital     varchar(30) not null, 
    primary key pk(cn, lang),
    index       name(name),
    foreign key cn(cn)
     references com_country(id) on delete cascade
    )
    character set utf8 collate utf8_general_ci;

/* .section.  data */

insert into com_lang(id, code, sample, name_en, loc_en) values
    (0, '', 'Default', 'Default', 'Default'), 
    (1, 'eo', 'Esperanto', 'Esperanto', 'Esperlando'), 
    (1028, 'zh_TW', 'Chinese', 'Chinese', 'Taiwan, Region'), 
    (1033, 'en_US', 'English', 'English', 'United States'), 
    (1036, 'fr', 'French', 'French', 'France'), 
    (1040, 'it', 'Italian', 'Italian', 'Italy'), 
    (1041, 'jp', x'e697a5e69cace8aa3f', 'Japanese', 'Japan'), 
    (1042, 'kr', concat(x'ed959ceab5b4', '(Extended Wansung)'), 'Korean', 'Korea'), 
    (1045, 'pl', 'Polish', 'Polish', 'Poland'), 
    (1046, 'pt', 'Portuguese', 'Portuguese', 'Brazil'), 
    (1049, 'ru', 'Russian', 'Russian', 'Russia'), 
    (2052, 'zh', x'e4b8ade69687efbc88e7ae80e4bd93efbc89', 'Chinese', 'PRC'), 
    (2055, 'de', 'Deutsch', 'German', 'Switzerland'), 
    (2057, 'en_UK', 'English', 'English', 'United Kingdom'); 

insert into com_lang_pref(lang, pr) values
    (2052, 0),                          /* zh_CN */
    (1028, 10),                         /* zh_TW */
    (1, 20),                            /* eo */
    (1041, 30),                         /* jp */
    (1033, 40),                         /* en_US */
    (2057, 50),                         /* en_UK */
    (1042, 60),                         /* kr */
    (0, 1000);                          /* default */

insert into com_continent(id) values
    (1),                                /* africa */
    (2),                                /* america north */
    (3),                                /* america south */
    (4),                                /* antarctia */
    (5),                                /* asia */
    (6),                                /* europe */
    (7);                                /* oceania */

insert into com_continent_ml(lang, continent, name) values
    (0, 1, 'Africa'),
    (0, 2, 'North America'),
    (0, 3, 'South America'),
    (0, 4, 'Antarctia'),
    (0, 5, 'Asia'),
    (0, 6, 'Europe'),
    (0, 7, 'Oceania'),
    (2052, 1, '非洲'),
    (2052, 2, '北美洲'),
    (2052, 3, '南美洲'),
    (2052, 4, '南极洲'),
    (2052, 5, '亚洲'),
    (2052, 6, '欧洲'),
    (2052, 7, '欧洲'); 

insert into com_country(id, continent, name_en, tz, size, telp) values
    (1, 5, 'China', 800, 9600000, 86),
    (2, 2, 'America', -600, 9372614, 1);

insert into com_country_ml(lang, cn, name, capital) values
    (0, 1, 'China', 'Beijing'),
    (0, 2, 'America', 'Washington D.C.'),
    (2052, 1, '中国', '北京'),
    (2052, 2, '美国', '华盛顿'),
    (1, 1, 'Chino', 'Pekino'); 

