import type { BigDecimal, int, short } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedValidators";
import type UomRow from "lily-basic/src/net/bodz/lily/schema/util/UomRow";

import type Artifact from "./Artifact";
import type ArtifactCategory from "./ArtifactCategory";
import type ArtifactPhase from "./ArtifactPhase";
import type ArtifactType from "./ArtifactType";
import type _Artifact_stuff_TypeInfo from "./_Artifact_stuff_TypeInfo";

export class _Artifact_stuff_Validators extends CoImagedValidators {

    constructor(type: _Artifact_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Artifact_stuff_TypeInfo;
    }

    validateSkuCode(val: string) {
    }

    validateBarCode(val: string) {
    }

    validateRfidCode(val: string) {
    }

    validateModelName(val: string) {
    }

    validateFinish(val: short) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateType(val: ArtifactType) {
    }

    validateProto(val: Artifact) {
    }

    validatePhase(val: ArtifactPhase) {
    }

    validateUom(val: UomRow) {
    }

    validateCategory(val: ArtifactCategory) {
    }

}

export default _Artifact_stuff_Validators;
