
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { VAppCat } from "./VAppCat";
import type { _VAppCat_stuff_Type } from "./_VAppCat_stuff_Type";

export class _VAppCat_stuff extends CoEntity<Integer> {
    static TYPE = new _VAppCat_stuff_Type();

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: VAppCat;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
