import SalesCategoryTypeInfo from "./SalesCategoryTypeInfo";
import _SalesCategory_stuff from "./_SalesCategory_stuff";

export class SalesCategory extends _SalesCategory_stuff<SalesCategory> {

    static _typeInfo: SalesCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SalesCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default SalesCategory;
