import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type Artifact from "../art/Artifact";
import type _OffStoreItem_stuff_TypeInfo from "./_OffStoreItem_stuff_TypeInfo";

export class _OffStoreItem_stuff_Validators extends CoEntityValidators {

    constructor(type: _OffStoreItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _OffStoreItem_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateBatch(val: JsonVariant) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _OffStoreItem_stuff_Validators;
