import type { int } from "@skeljs/core/src/lang/basetype";

import AbstractDefinition from "./AbstractDefinition";
import type TagGroupDef from "./TagGroupDef";

export class _TagDef_stuff<this_t> extends AbstractDefinition<this_t> {

    tagGroup: TagGroupDef;
    tagGroupId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TagDef_stuff;
