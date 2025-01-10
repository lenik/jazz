import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEventValidators";

import type Artifact from "../art/Artifact";
import type TransportOrder from "./TransportOrder";
import type _TransportOrderItem_stuff_TypeInfo from "./_TransportOrderItem_stuff_TypeInfo";

export class _TransportOrderItem_stuff_Validators extends CoEventValidators {

    constructor(type: _TransportOrderItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TransportOrderItem_stuff_TypeInfo;
    }

    validateQuantity(val: BigDecimal) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateAmount(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

    validateOrder(val: TransportOrder) {
    }

}

export default _TransportOrderItem_stuff_Validators;
