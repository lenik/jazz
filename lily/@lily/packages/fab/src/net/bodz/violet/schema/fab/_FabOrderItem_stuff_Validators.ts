import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedEventValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventValidators";
import type Artifact from "lily-violet/src/net/bodz/violet/schema/art/Artifact";

import type FabOrder from "./FabOrder";
import type _FabOrderItem_stuff_TypeInfo from "./_FabOrderItem_stuff_TypeInfo";

export class _FabOrderItem_stuff_Validators extends CoImagedEventValidators {

    constructor(type: _FabOrderItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabOrderItem_stuff_TypeInfo;
    }

    validateResale(val: boolean) {
    }

    validateAltLabel(val: string) {
    }

    validateAltSpec(val: string) {
    }

    validateAltUom(val: string) {
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

    validateOrder(val: FabOrder) {
    }

}

export default _FabOrderItem_stuff_Validators;
