import ShopTypeInfo from "./ShopTypeInfo";
import _Shop_stuff from "./_Shop_stuff";

export class Shop extends _Shop_stuff {

    static _typeInfo: ShopTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Shop;
