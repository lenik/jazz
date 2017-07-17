
    create sequence uom_seq start with 1000;
    create table uom(
        id          int primary key default nextval('uom_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label

    -- props:
        -- qty.property (aka. uomprop)
        -- qty.digits
--\mixin lily.mixin.Props

        prop        varchar(30) not null default 'Measure',

        std         int
            references uom(id) on update cascade,

        scale       double precision not null default 0
    );

    create index uom_label          on uom(label);

    insert into uom(id, code, label, prop) values(1, 'pcs', '件', '数量');

    insert into uom(id, code, label, prop) values(100, 'm', '米', '长度');
    insert into uom(id, code, label, prop, std, scale) values(101, 'mm',     '毫米',   '长度', 100, 0.001);
    insert into uom(id, code, label, prop, std, scale) values(102, 'cm',     '厘米',   '长度', 100, 0.01);
    insert into uom(id, code, label, prop, std, scale) values(103, 'dm',     '分米',   '长度', 100, 0.1);
    insert into uom(id, code, label, prop, std, scale) values(104, 'km',     '千米',   '长度', 100, 1000);
    insert into uom(id, code, label, prop, std, scale) values(105, 'inch',   '英寸',   '长度', 100, 0.0254);
    insert into uom(id, code, label, prop, std, scale) values(106, 'foot',   '英尺',   '长度', 100, 0.3048);
    insert into uom(id, code, label, prop, std, scale) values(107, 'mile',   '英里',   '长度', 100, 1609.34);
    insert into uom(id, code, label, prop) values(200, 'm2', '平方米', '面积');
    insert into uom(id, code, label, prop, std, scale) values(201, 'mm2',    '平方毫米', '面积', 200,  0.000001);
    insert into uom(id, code, label, prop, std, scale) values(202, 'cm2',    '平方厘米', '面积', 200,  0.0001);
    insert into uom(id, code, label, prop, std, scale) values(203, 'dm2',    '平方分米', '面积', 200,  0.01);
    insert into uom(id, code, label, prop, std, scale) values(204, 'km2',    '平方千米', '面积', 200, 1000000);

    insert into uom(id, code, label, prop) values(300, 'm3', '立方米', '体积');
    insert into uom(id, code, label, prop, std, scale) values(350, 'L',      '升',    '容量', 300, 0.001);
    insert into uom(id, code, label, prop, std, scale) values(351, 'mL',     '毫升',   '容量', 300, 0.000001);

    insert into uom(id, code, label, prop) values(400, 'g', '克', '质量');
    insert into uom(id, code, label, prop, std, scale) values(401, 'mg',     '毫克',   '质量', 400, 0.001);
    insert into uom(id, code, label, prop, std, scale) values(402, 'kg',     '千克',   '质量', 400, 1000);
    insert into uom(id, code, label, prop, std, scale) values(403, 't',      '吨',    '质量', 400, 1000000);
    insert into uom(id, code, label, prop, std, scale) values(410, 'lb',     '磅',    '质量', 400, 453.59237);
    insert into uom(id, code, label, prop, std, scale) values(411, 'oz',     '盎司',   '质量', 400, 28.3495231);
    insert into uom(id, code, label, prop, std, scale) values(420, '斤',      '斤',    '质量', 400, 500);
    insert into uom(id, code, label, prop, std, scale) values(421, '两',      '两',    '质量', 400, 50);
    insert into uom(id, code, label, prop, std, scale) values(422, '钱',      '钱',    '质量', 400, 5);
    insert into uom(id, code, label, prop, std, scale) values(430, 'ct',     '克拉',   '质量', 400, 0.2);
    insert into uom(id, code, label, prop) values(480, 'N', '牛顿', '力');
    insert into uom(id, code, label, prop, std, scale) values(481, 'kN',     '千牛顿',  '力', 480, 1);

    /*
    insert into uom(id, code, label, prop, std, scale) values(901, '个', '个', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(902, '张', '张', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(903, '只', '只', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(904, '支', '支', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(905, '套', '套', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(907, '台', '台', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(908, '瓶', '瓶', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(909, '桶', '桶', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(910, '箱', '箱', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(911, '粒', '粒', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(912, '条', '条', '数量', 1, 1);
    insert into uom(id, code, label, prop, std, scale) values(913, '盒', '盒', '数量', 1, 1);
    */

