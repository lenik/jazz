import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipal from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipal";

import Group from "./Group";
import GroupType from "./GroupType";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class _Group_stuff extends CoPrincipal {
    static TYPE = new _Group_stuff_TypeInfo();


    parent?: Group;
    parentId?: integer;

    type: GroupType;
    typeId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Group_stuff;
