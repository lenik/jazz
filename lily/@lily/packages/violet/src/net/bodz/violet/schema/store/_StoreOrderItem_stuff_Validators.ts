import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedEventValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventValidators";

import type Artifact from "../art/Artifact";
import type Region from "./Region";
import type StoreOrder from "./StoreOrder";
import type _StoreOrderItem_stuff_TypeInfo from "./_StoreOrderItem_stuff_TypeInfo";

export class _StoreOrderItem_stuff_Validators extends CoImagedEventValidators {

    constructor(type: _StoreOrderItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _StoreOrderItem_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateSerial(val: long) {
    }

    validateExpire(val: OffsetDateTime) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateAmount(val: BigDecimal) {
    }

    validateNotes(val: string) {
    }

    validateArtifact(val: Artifact) {
    }

    validateOrder(val: StoreOrder) {
    }

    validateRegion(val: Region) {
    }

}

export default _StoreOrderItem_stuff_Validators;
