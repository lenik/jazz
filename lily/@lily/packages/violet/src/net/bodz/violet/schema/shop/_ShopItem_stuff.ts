import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoImagedEvent from "lily-basic/src/net/bodz/lily/concrete/CoImagedEvent";

import type Artifact from "../art/Artifact";
import type Shop from "./Shop";
import type ShopItemCategory from "./ShopItemCategory";
import _ShopItem_stuff_TypeInfo from "./_ShopItem_stuff_TypeInfo";

export class _ShopItem_stuff extends CoImagedEvent<long> {

    static _typeInfo: _ShopItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ShopItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;
    price: BigDecimal;
    quantity: BigDecimal;

    category?: ShopItemCategory;
    categoryId?: int;

    shop?: Shop;
    shopId?: int;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ShopItem_stuff;
