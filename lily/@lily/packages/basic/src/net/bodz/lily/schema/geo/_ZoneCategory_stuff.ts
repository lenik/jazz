
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { ZoneCategory } from "./ZoneCategory";
import type { _ZoneCategory_stuff_Type } from "./_ZoneCategory_stuff_Type";

export class _ZoneCategory_stuff extends CoEntity<Integer> {
    static TYPE = new _ZoneCategory_stuff_Type();

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: ZoneCategory;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
