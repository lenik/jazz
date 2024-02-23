import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import TagGroupDef from "./TagGroupDef";
import _TagDef_stuff_Type from "./_TagDef_stuff_Type";

export class _TagDef_stuff extends CoEntity<integer> {
    static TYPE = new _TagDef_stuff_Type();

    id: integer;

    tagGroup: TagGroupDef;
    tagGroupId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _TagDef_stuff;
