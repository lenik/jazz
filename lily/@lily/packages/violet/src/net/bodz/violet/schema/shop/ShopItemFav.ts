import ShopItemFavTypeInfo from "./ShopItemFavTypeInfo";
import _ShopItemFav_stuff from "./_ShopItemFav_stuff";

export class ShopItemFav extends _ShopItemFav_stuff {

    static _typeInfo: ShopItemFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopItemFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ShopItemFav;
