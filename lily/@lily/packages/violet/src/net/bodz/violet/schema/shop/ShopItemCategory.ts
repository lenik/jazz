import ShopItemCategoryTypeInfo from "./ShopItemCategoryTypeInfo";
import _ShopItemCategory_stuff from "./_ShopItemCategory_stuff";

export class ShopItemCategory extends _ShopItemCategory_stuff<ShopItemCategory> {

    static _typeInfo: ShopItemCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopItemCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ShopItemCategory;
