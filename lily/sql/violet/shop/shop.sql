--\mixin lily.template.a-cat shop
--\mixin lily.template.a-tag shop

    create sequence shop_seq start with 1000;
    create table shop(
        id          int primary key default nextval('shop_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Props

        hydm    int
    );

    create index shop_label         on shop(label);
    create index shop_lastmod       on shop(lastmod desc);
    create index shop_priority      on shop(priority);
    create index shop_state         on shop(state);
    create index shop_uid_acl       on shop(uid, acl);

--\mixin lily.template.a-tags shop
--\mixin lily.template.a-favs shop int label
