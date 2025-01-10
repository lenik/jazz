import type { BigDecimal, List, int } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";

import TransportOrder from "../tran/TransportOrder";
import SalesOrderItem from "./SalesOrderItem";
import SalesOrderTypeInfo from "./SalesOrderTypeInfo";
import _SalesOrder_stuff from "./_SalesOrder_stuff";

export class SalesOrder extends _SalesOrder_stuff {

    static _typeInfo: SalesOrderTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SalesOrderTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    beginTime?: OffsetDateTime
    deadline?: OffsetDateTime
    deliveries?: List<TransportOrder>
    endTime?: OffsetDateTime
    items?: List<SalesOrderItem>
    length: int
    orderTime?: OffsetDateTime
    totalAmount?: BigDecimal
    totalQuantity?: BigDecimal

    constructor(o?: any) {
        super(o);
    }
}

export default SalesOrder;
