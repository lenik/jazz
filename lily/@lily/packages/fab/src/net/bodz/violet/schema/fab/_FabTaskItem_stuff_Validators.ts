import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEventValidators";

import type ArtifactModel from "../art/ArtifactModel";
import type FabTask from "./FabTask";
import type _FabTaskItem_stuff_TypeInfo from "./_FabTaskItem_stuff_TypeInfo";

export class _FabTaskItem_stuff_Validators extends CoEventValidators {

    constructor(type: _FabTaskItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTaskItem_stuff_TypeInfo;
    }

    validateDeadline(val: Timestamp) {
    }

    validateStatus(val: string) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateBatch(val: JsonVariant) {
    }

    validateTrackCount(val: int) {
    }

    validateTask(val: FabTask) {
    }

    validateModel(val: ArtifactModel) {
    }

}

export default _FabTaskItem_stuff_Validators;
