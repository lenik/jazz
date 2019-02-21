--\import data.china-2013

    update zone set parent = p.id, depth = 2 from zone p
        where length(zone.code) = 4 and substr(zone.code, 1, 2) = p.code;
    update zone set parent = p.id, depth = 3 from zone p
        where length(zone.code) = 6 and substr(zone.code, 1, 4) = p.code;
    update zone set parent = p.id, depth = 4 from zone p
        where length(zone.code) > 6 and substr(zone.code, 1, 6) = p.code;
    update zone set parent = p.id, depth = p.depth + 1 from zone p
        where zone.parent is null and length(zone.code) > length(p.code)
        and substr(zone.code, 1, length(p.code)) = p.code;
    update zone set parent = 86, depth = 1 where parent is null and code is not null;

