import type { BigDecimal, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type Artifact from "../art/Artifact";
import type _SellPrice_stuff_TypeInfo from "./_SellPrice_stuff_TypeInfo";

export class _SellPrice_stuff_Validators extends IdEntityValidators {

    constructor(type: _SellPrice_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SellPrice_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _SellPrice_stuff_Validators;
