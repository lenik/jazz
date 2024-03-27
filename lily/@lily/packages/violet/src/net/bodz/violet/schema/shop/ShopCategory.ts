import ShopCategoryTypeInfo from "./ShopCategoryTypeInfo";
import _ShopCategory_stuff from "./_ShopCategory_stuff";

export class ShopCategory extends _ShopCategory_stuff<ShopCategory> {

    static _typeInfo: ShopCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ShopCategory;
