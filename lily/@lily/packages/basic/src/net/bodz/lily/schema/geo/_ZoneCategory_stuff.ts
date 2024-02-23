import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ZoneCategory from "./ZoneCategory";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class _ZoneCategory_stuff extends CoEntity<integer> {
    static TYPE = new _ZoneCategory_stuff_TypeInfo();

    id: integer;
    name?: string;
    depth: integer;
    refCount: integer;

    parent?: ZoneCategory;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ZoneCategory_stuff;
