import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type Artifact from "../art/Artifact";
import type _SellPrice_stuff_TypeInfo from "./_SellPrice_stuff_TypeInfo";

export class _SellPrice_stuff_Validators extends CoEntityValidators {

    constructor(type: _SellPrice_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SellPrice_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCode(val: string) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _SellPrice_stuff_Validators;
