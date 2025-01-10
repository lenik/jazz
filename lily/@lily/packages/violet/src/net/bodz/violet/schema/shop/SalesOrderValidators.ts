import type { BigDecimal, List, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";

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

    validateBeginTime(val: OffsetDateTime) {
    }

    validateDeadline(val: OffsetDateTime) {
    }

    validateDeliveries(val: List<TransportOrder>) {
    }

    validateEndTime(val: OffsetDateTime) {
    }

    validateItems(val: List<SalesOrderItem>) {
    }

    validateLength(val: int) {
    }

    validateOrderTime(val: OffsetDateTime) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

}

export default SalesOrderValidators;
