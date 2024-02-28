import TagDefTypeInfo from "./TagDefTypeInfo";
import _TagDef_stuff from "./_TagDef_stuff";

export class TagDef extends _TagDef_stuff {
    static _typeInfo: TagDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new TagDefTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagDef;
