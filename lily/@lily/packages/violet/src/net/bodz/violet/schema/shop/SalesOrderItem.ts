import type { BigDecimal } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";

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
    beginTime?: OffsetDateTime
    deadline?: OffsetDateTime
    endTime?: OffsetDateTime
    orderTime?: OffsetDateTime

    constructor(o?: any) {
        super(o);
    }
}

export default SalesOrderItem;
