import type { BigDecimal, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import type Organization from "lily-basic/src/net/bodz/lily/schema/contact/Organization";

import type SalesOrder from "../shop/SalesOrder";
import type StoreOrder from "../store/StoreOrder";
import AbstractTransportOrderValidators from "./AbstractTransportOrderValidators";
import type TransportCategory from "./TransportCategory";
import type TransportOrder from "./TransportOrder";
import type TransportPhase from "./TransportPhase";
import type _TransportOrder_stuff_TypeInfo from "./_TransportOrder_stuff_TypeInfo";

export class _TransportOrder_stuff_Validators extends AbstractTransportOrderValidators {

    constructor(type: _TransportOrder_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TransportOrder_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateShipcost(val: BigDecimal) {
    }

    validateLength(val: int) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validatePrev(val: TransportOrder) {
    }

    validateShipper(val: Organization) {
    }

    validateCategory(val: TransportCategory) {
    }

    validateSalesOrder(val: SalesOrder) {
    }

    validatePhase(val: TransportPhase) {
    }

    validateStoreodr(val: StoreOrder) {
    }

}

export default _TransportOrder_stuff_Validators;
