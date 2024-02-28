import CoTag from "../../concrete/CoTag";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class _PostTagType_stuff<this_t> extends CoTag<this_t> {
    static _typeInfo: _PostTagType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PostTagType_stuff_TypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
    }
}

export default _PostTagType_stuff;
