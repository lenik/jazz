import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type Artifact from "../art/Artifact";
import type _OffStoreItem_stuff_TypeInfo from "./_OffStoreItem_stuff_TypeInfo";

export class _OffStoreItem_stuff_Validators extends IdEntityValidators {

    constructor(type: _OffStoreItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _OffStoreItem_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _OffStoreItem_stuff_Validators;
