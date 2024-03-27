import StoreCategoryTypeInfo from "./StoreCategoryTypeInfo";
import _StoreCategory_stuff from "./_StoreCategory_stuff";

export class StoreCategory extends _StoreCategory_stuff<StoreCategory> {

    static _typeInfo: StoreCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StoreCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default StoreCategory;
