import List from "../../../../../java/util/List";
import TagDef from "./TagDef";
import TagGroupDefTypeInfo from "./TagGroupDefTypeInfo";
import _TagGroupDef_stuff from "./_TagGroupDef_stuff";

export class TagGroupDef extends _TagGroupDef_stuff {
    static _typeInfo: TagGroupDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new TagGroupDefTypeInfo();
        return this._typeInfo;
    }


    ortho: boolean
    tags?: List<TagDef>

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagGroupDef;
