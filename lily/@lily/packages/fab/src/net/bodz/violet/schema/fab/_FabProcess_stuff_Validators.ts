import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEventValidators";

import type ArtifactModel from "../art/ArtifactModel";
import type FabProcess from "./FabProcess";
import type FabStdProcess from "./FabStdProcess";
import type FabTask from "./FabTask";
import type _FabProcess_stuff_TypeInfo from "./_FabProcess_stuff_TypeInfo";

export class _FabProcess_stuff_Validators extends CoEventValidators {

    constructor(type: _FabProcess_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabProcess_stuff_TypeInfo;
    }

    validateQuantity(val: BigDecimal) {
    }

    validateBatch(val: JsonVariant) {
    }

    validateSince(val: OffsetDateTime) {
    }

    validateDeadline(val: OffsetDateTime) {
    }

    validateTrackCount(val: int) {
    }

    validateStandard(val: FabStdProcess) {
    }

    validateParent(val: FabProcess) {
    }

    validateOutput(val: ArtifactModel) {
    }

    validateTask(val: FabTask) {
    }

}

export default _FabProcess_stuff_Validators;
