import type { int } from "@skeljs/core/src/lang/basetype";
import FavRecord from "@lily/basic/src/net/bodz/lily/concrete/FavRecord";

import type Shop from "./Shop";
import _ShopFav_stuff_TypeInfo from "./_ShopFav_stuff_TypeInfo";

export class _ShopFav_stuff extends FavRecord {

    static _typeInfo: _ShopFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ShopFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    shop: Shop;
    shopId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ShopFav_stuff;
