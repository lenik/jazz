import type { List } from "skel01-core/src/lang/basetype";

import TagDef from "./TagDef";
import TagGroupDefTypeInfo from "./TagGroupDefTypeInfo";
import _TagGroupDef_stuff from "./_TagGroupDef_stuff";

export class TagGroupDef extends _TagGroupDef_stuff<TagGroupDef> {

    static _typeInfo: TagGroupDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TagGroupDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    ortho: boolean
    tags?: List<TagDef>

    constructor(o?: any) {
        super(o);
    }
}

export default TagGroupDef;
