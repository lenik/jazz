--\import data.china-2013

    update zone set parent = p.id from zone p
        where length(zone.code) = 4 and substr(zone.code, 1, 2) = p.code;
    update zone set parent = p.id from zone p
        where length(zone.code) = 6 and substr(zone.code, 1, 4) = p.code;
    update zone set parent = p.id from zone p
        where length(zone.code) > 6 and substr(zone.code, 1, 6) = p.code;
    update zone set parent = p.id from zone p
        where zone.parent is null and length(zone.code) > length(p.code)
        and substr(zone.code, 1, length(p.code)) = p.code;
    update zone set parent = 86 where parent is null and code is not null;

