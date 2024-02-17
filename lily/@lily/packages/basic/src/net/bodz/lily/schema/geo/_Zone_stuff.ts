
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Object } from "../../../../../java/lang/Object";
import type { Zone } from "./Zone";
import type { ZoneCategory } from "./ZoneCategory";
import type { _Zone_stuff_Type } from "./_Zone_stuff_Type";

export class _Zone_stuff extends CoEntity<Integer> {
    static TYPE = new _Zone_stuff_Type();

    id: int;
    code?: string;
    country?: string;
    depth: int;
    telCode?: string;
    postCode?: string;
    data?: Object;

    parent?: Zone;
    parentId?: integer;

    category: ZoneCategory;
    categoryId: int;

    constructor(o: any) {
        super(o);
    }
}
