import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import CoImagedEvent from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEvent";
import type Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import type FabOrder from "./FabOrder";
import _FabOrderItem_stuff_TypeInfo from "./_FabOrderItem_stuff_TypeInfo";

export class _FabOrderItem_stuff extends CoImagedEvent<long> {

    static _typeInfo: _FabOrderItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabOrderItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    resale: boolean;
    altLabel?: string;
    altSpec?: string;
    altUom?: string;
    quantity: BigDecimal;
    price: BigDecimal;
    amount: BigDecimal;
    notes?: string;

    artifact: Artifact;
    artifactId: int;

    order: FabOrder;
    orderId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabOrderItem_stuff;
