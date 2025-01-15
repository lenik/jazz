import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedEventValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventValidators";

import type Artifact from "../art/Artifact";
import type SalesOrder from "./SalesOrder";
import type ShopItem from "./ShopItem";
import type _SalesOrderItem_stuff_TypeInfo from "./_SalesOrderItem_stuff_TypeInfo";

export class _SalesOrderItem_stuff_Validators extends CoImagedEventValidators {

    constructor(type: _SalesOrderItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SalesOrderItem_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateAmount(val: BigDecimal) {
    }

    validateN1(val: BigDecimal) {
    }

    validateOrder(val: SalesOrder) {
    }

    validateShopItem(val: ShopItem) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _SalesOrderItem_stuff_Validators;
