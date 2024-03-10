import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type ShopItem from "./ShopItem";
import _CartItem_stuff_TypeInfo from "./_CartItem_stuff_TypeInfo";

export class _CartItem_stuff extends CoEntity<long> {

    static _typeInfo: _CartItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _CartItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    price: BigDecimal;
    quantity: BigDecimal;

    shopItem: ShopItem;
    shopItemId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _CartItem_stuff;
