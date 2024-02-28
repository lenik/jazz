import CoTag from "../../concrete/CoTag";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class _PersonTagType_stuff<this_t> extends CoTag<this_t> {
    static _typeInfo: _PersonTagType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PersonTagType_stuff_TypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
    }
}

export default _PersonTagType_stuff;
