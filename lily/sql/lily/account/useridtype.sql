
    create sequence useridtype_seq start with 1000;

    create table useridtype(
        id          int primary key default nextval('useridtype_seq'),
--\mixin lily.mixin.LabelExVer
        dummy       int
    );

    insert into useridtype(id, label) values(10, 'Alipay');
    insert into useridtype(id, label) values(11, 'WeChat');
    insert into useridtype(id, label) values(12, 'QQ');
