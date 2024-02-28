import CoTag from "../../concrete/CoTag";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class _ArticleTagType_stuff<this_t> extends CoTag<this_t> {
    static _typeInfo: _ArticleTagType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ArticleTagType_stuff_TypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTagType_stuff;
