import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";

import type Artifact from "../art/Artifact";
import type TransportOrder from "./TransportOrder";
import _TransportOrderItem_stuff_TypeInfo from "./_TransportOrderItem_stuff_TypeInfo";

export class _TransportOrderItem_stuff extends CoEvent<long> {

    static _typeInfo: _TransportOrderItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TransportOrderItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    quantity: BigDecimal;
    price: BigDecimal;
    amount: BigDecimal;

    artifact?: Artifact;
    artifactId?: int;

    order: TransportOrder;
    orderId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TransportOrderItem_stuff;
