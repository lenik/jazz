import type { BigDecimal, List, int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

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

    beginTime?: ZonedDateTime
    deadline?: ZonedDateTime
    deliveries?: List<TransportOrder>
    endTime?: ZonedDateTime
    items?: List<SalesOrderItem>
    length: int
    orderTime?: ZonedDateTime
    totalAmount?: BigDecimal
    totalQuantity?: BigDecimal

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default SalesOrder;
