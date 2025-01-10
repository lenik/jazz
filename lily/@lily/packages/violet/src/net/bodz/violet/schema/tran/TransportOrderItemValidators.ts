import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type SalesOrder from "../shop/SalesOrder";
import type SalesOrderItem from "../shop/SalesOrderItem";
import type TransportOrderItemTypeInfo from "./TransportOrderItemTypeInfo";
import _TransportOrderItem_stuff_Validators from "./_TransportOrderItem_stuff_Validators";

export class TransportOrderItemValidators extends _TransportOrderItem_stuff_Validators {

    constructor(type: TransportOrderItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TransportOrderItemTypeInfo;
    }

    validateAmount(val: BigDecimal) {
    }

    validateSalesOrder(val: SalesOrder) {
    }

    validateSalesOrderItem(val: SalesOrderItem) {
    }

}

export default TransportOrderItemValidators;
