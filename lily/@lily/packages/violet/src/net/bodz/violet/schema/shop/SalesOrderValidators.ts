import type { BigDecimal, List, int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TransportOrder from "../tran/TransportOrder";
import type SalesOrderItem from "./SalesOrderItem";
import type SalesOrderTypeInfo from "./SalesOrderTypeInfo";
import _SalesOrder_stuff_Validators from "./_SalesOrder_stuff_Validators";

export class SalesOrderValidators extends _SalesOrder_stuff_Validators {

    constructor(type: SalesOrderTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SalesOrderTypeInfo;
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateDeadline(val: ZonedDateTime) {
    }

    validateDeliveries(val: List<TransportOrder>) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateItems(val: List<SalesOrderItem>) {
    }

    validateLength(val: int) {
    }

    validateOrderTime(val: ZonedDateTime) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

}

export default SalesOrderValidators;
