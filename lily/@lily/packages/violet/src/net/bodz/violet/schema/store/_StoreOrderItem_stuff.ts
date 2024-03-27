import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import CoImagedEvent from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEvent";

import type Artifact from "../art/Artifact";
import type Region from "./Region";
import type StoreOrder from "./StoreOrder";
import _StoreOrderItem_stuff_TypeInfo from "./_StoreOrderItem_stuff_TypeInfo";

export class _StoreOrderItem_stuff extends CoImagedEvent<long> {

    static _typeInfo: _StoreOrderItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _StoreOrderItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;
    serial?: long;
    expire?: Timestamp;
    quantity: BigDecimal;
    price: BigDecimal;
    amount: BigDecimal;
    notes?: string;

    artifact: Artifact;
    artifactId: int;

    order: StoreOrder;
    orderId: long;

    region: Region;
    regionId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _StoreOrderItem_stuff;