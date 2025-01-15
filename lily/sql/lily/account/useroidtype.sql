
    create sequence useroidtype_seq start with 1000;

    create table useroidtype(
        id          int primary key default nextval('useroidtype_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
        dummy       int
    );

    comment on table useroidtype is 'Type of Open ID';

    insert into useroidtype(id, label) values
        (1, 'email'),
        (2, 'mobile'),
        (10, 'Alipay'),
        (11, 'WeChat'),
        (12, 'QQ');
