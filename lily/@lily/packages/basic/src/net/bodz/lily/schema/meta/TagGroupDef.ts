import { TagDef } from "./TagDef";
import _TagGroupDef_stuff from "./_TagGroupDef_stuff";
import { _TagGroupDef_stuff_Type } from "./_TagGroupDef_stuff_Type";

export class TagGroupDef extends _TagGroupDef_stuff {
    static TYPE = new _TagGroupDef_stuff_Type();

    ortho: boolean
    tags?: TagDef[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagGroupDef;
