
import type { List } from "../../../../../java/util/List";
import { * as validators } from "./PersonValidators";
import type { _TagGroupDef_stuff } from "./_TagGroupDef_stuff";

export class TagGroupDef extends _TagGroupDef_stuff {
    static TYPE = new TagGroupDefType();

    ortho: boolean
    tags?: List

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
