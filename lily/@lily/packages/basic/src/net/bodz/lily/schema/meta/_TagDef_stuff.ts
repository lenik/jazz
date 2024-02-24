import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type TagGroupDef from "./TagGroupDef";
import _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

export class _TagDef_stuff extends CoEntity<integer> {
    static TYPE = new _TagDef_stuff_TypeInfo();

    id: integer;

    tagGroup: TagGroupDef;
    tagGroupId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _TagDef_stuff;
