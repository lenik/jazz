import type { long } from "skel01-core/src/lang/basetype";
import FavRecord from "lily-basic/src/net/bodz/lily/concrete/FavRecord";

import type ShopItem from "./ShopItem";
import _ShopItemFav_stuff_TypeInfo from "./_ShopItemFav_stuff_TypeInfo";

export class _ShopItemFav_stuff extends FavRecord {

    static _typeInfo: _ShopItemFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ShopItemFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    shopItem: ShopItem;
    shopItemId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ShopItemFav_stuff;
