
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { TagGroupDef } from "./TagGroupDef";
import type { _TagDef_stuff_Type } from "./_TagDef_stuff_Type";

export class _TagDef_stuff extends CoEntity<Integer> {
    static TYPE = new _TagDef_stuff_Type();

    id: int;

    tagGroup: TagGroupDef;
    tagGroupId: int;

    constructor(o: any) {
        super(o);
    }
}
