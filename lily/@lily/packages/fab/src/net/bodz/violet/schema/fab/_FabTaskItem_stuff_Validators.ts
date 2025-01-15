import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoEventValidators from "lily-basic/src/net/bodz/lily/concrete/CoEventValidators";

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

    validateDeadline(val: OffsetDateTime) {
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
