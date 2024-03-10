import ShopFavTypeInfo from "./ShopFavTypeInfo";
import _ShopFav_stuff from "./_ShopFav_stuff";

export class ShopFav extends _ShopFav_stuff {

    static _typeInfo: ShopFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ShopFav;
