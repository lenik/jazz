
import type { CoPrincipal } from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipal";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Group } from "./Group";
import type { GroupType } from "./GroupType";
import type { _Group_stuff_Type } from "./_Group_stuff_Type";

export class _Group_stuff extends CoPrincipal {
    static TYPE = new _Group_stuff_Type();


    parent?: Group;
    parentId?: integer;

    type: GroupType;
    typeId: int;

    constructor(o: any) {
        super(o);
    }
}
