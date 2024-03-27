import VAppCategoryTypeInfo from "./VAppCategoryTypeInfo";
import _VAppCategory_stuff from "./_VAppCategory_stuff";

export class VAppCategory extends _VAppCategory_stuff<VAppCategory> {

    static _typeInfo: VAppCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VAppCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default VAppCategory;
