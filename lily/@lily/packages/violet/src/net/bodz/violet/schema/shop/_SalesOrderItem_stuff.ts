import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoImagedEvent from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEvent";

import type Artifact from "../art/Artifact";
import type SalesOrder from "./SalesOrder";
import type ShopItem from "./ShopItem";
import _SalesOrderItem_stuff_TypeInfo from "./_SalesOrderItem_stuff_TypeInfo";

export class _SalesOrderItem_stuff extends CoImagedEvent<long> {

    static _typeInfo: _SalesOrderItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _SalesOrderItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;
    quantity: BigDecimal;
    price: BigDecimal;
    amount: BigDecimal;
    n1: BigDecimal;

    order: SalesOrder;
    orderId: long;

    shopItem?: ShopItem;
    shopItemId?: long;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SalesOrderItem_stuff;
