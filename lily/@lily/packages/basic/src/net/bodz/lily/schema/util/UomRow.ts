import UomRowTypeInfo from "./UomRowTypeInfo";
import _UomRow_stuff from "./_UomRow_stuff";

export class UomRow extends _UomRow_stuff {

    static _typeInfo: UomRowTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UomRowTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default UomRow;
