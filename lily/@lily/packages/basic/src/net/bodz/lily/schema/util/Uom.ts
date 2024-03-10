import UomTypeInfo from "./UomTypeInfo";
import _Uom_stuff from "./_Uom_stuff";

export class Uom extends _Uom_stuff<Uom> {

    static _typeInfo: UomTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UomTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Uom;
