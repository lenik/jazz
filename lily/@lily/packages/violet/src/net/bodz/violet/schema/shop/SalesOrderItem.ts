import type { BigDecimal } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import SalesOrderItemTypeInfo from "./SalesOrderItemTypeInfo";
import _SalesOrderItem_stuff from "./_SalesOrderItem_stuff";

export class SalesOrderItem extends _SalesOrderItem_stuff {

    static _typeInfo: SalesOrderItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SalesOrderItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    amount?: BigDecimal
    beginTime?: ZonedDateTime
    deadline?: ZonedDateTime
    endTime?: ZonedDateTime
    orderTime?: ZonedDateTime

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default SalesOrderItem;
