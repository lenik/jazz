
    create sequence useroidtype_seq start with 1000;

    create table useroidtype(
        id          int primary key default nextval('useroidtype_seq'),
--\mixin lily.mixin.LabelExVer
        dummy       int
    );

    insert into useroidtype(id, label) values(1, 'email');
    insert into useroidtype(id, label) values(2, 'mobile');
    insert into useroidtype(id, label) values(10, 'Alipay');
    insert into useroidtype(id, label) values(11, 'WeChat');
    insert into useroidtype(id, label) values(12, 'QQ');

