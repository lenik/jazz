import type { BigDecimal } from "skel01-core/src/lang/basetype";

import StoreOrderItemTypeInfo from "./StoreOrderItemTypeInfo";
import _StoreOrderItem_stuff from "./_StoreOrderItem_stuff";

export class StoreOrderItem extends _StoreOrderItem_stuff {

    static _typeInfo: StoreOrderItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StoreOrderItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    amount: BigDecimal

    constructor(o?: any) {
        super(o);
    }
}

export default StoreOrderItem;
