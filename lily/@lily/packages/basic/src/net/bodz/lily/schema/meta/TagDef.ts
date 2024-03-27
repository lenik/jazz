import TagDefTypeInfo from "./TagDefTypeInfo";
import _TagDef_stuff from "./_TagDef_stuff";

export class TagDef extends _TagDef_stuff<TagDef> {

    static _typeInfo: TagDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TagDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TagDef;
