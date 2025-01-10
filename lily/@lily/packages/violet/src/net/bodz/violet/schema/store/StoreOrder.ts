import type { BigDecimal, List, int } from "skel01-core/src/lang/basetype";

import StoreOrderItem from "./StoreOrderItem";
import StoreOrderTypeInfo from "./StoreOrderTypeInfo";
import _StoreOrder_stuff from "./_StoreOrder_stuff";

export class StoreOrder extends _StoreOrder_stuff {

    static _typeInfo: StoreOrderTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StoreOrderTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    items?: List<StoreOrderItem>
    length: int
    totalAmount?: BigDecimal
    totalQuantity?: BigDecimal

    constructor(o?: any) {
        super(o);
    }
}

export default StoreOrder;
