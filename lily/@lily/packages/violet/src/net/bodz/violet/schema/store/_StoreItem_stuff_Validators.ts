import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type Artifact from "../art/Artifact";
import type Region from "./Region";
import type _StoreItem_stuff_TypeInfo from "./_StoreItem_stuff_TypeInfo";

export class _StoreItem_stuff_Validators extends IdEntityValidators {

    constructor(type: _StoreItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _StoreItem_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateRegion(val: Region) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _StoreItem_stuff_Validators;
