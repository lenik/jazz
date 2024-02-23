import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import VAppCat from "./VAppCat";
import _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class _VAppCat_stuff extends CoEntity<integer> {
    static TYPE = new _VAppCat_stuff_TypeInfo();

    id: integer;
    name?: string;
    depth: integer;
    refCount: integer;

    parent?: VAppCat;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppCat_stuff;
