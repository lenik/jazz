import type { int, long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEventValidators";

import type FabOrder from "./FabOrder";
import type _FabTask_stuff_TypeInfo from "./_FabTask_stuff_TypeInfo";

export class _FabTask_stuff_Validators extends CoEventValidators {

    constructor(type: _FabTask_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTask_stuff_TypeInfo;
    }

    validateSince(val: OffsetDateTime) {
    }

    validateDeadline(val: OffsetDateTime) {
    }

    validateProcessCount(val: int) {
    }

    validateTrackCount(val: int) {
    }

    validateOrder(val: FabOrder) {
    }

}

export default _FabTask_stuff_Validators;
