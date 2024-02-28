import VAppCatTypeInfo from "./VAppCatTypeInfo";
import _VAppCat_stuff from "./_VAppCat_stuff";

export class VAppCat extends _VAppCat_stuff {
    static _typeInfo: VAppCatTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new VAppCatTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppCat;
