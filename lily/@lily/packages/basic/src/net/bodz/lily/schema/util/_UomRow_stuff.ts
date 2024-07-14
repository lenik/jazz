import Uom from "./Uom";
import _UomRow_stuff_TypeInfo from "./_UomRow_stuff_TypeInfo";

export class _UomRow_stuff extends Uom {

    static _typeInfo: _UomRow_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UomRow_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default _UomRow_stuff;
