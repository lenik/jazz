import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoPhaseValidators from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _DiaryPhase_stuff_TypeInfo from "./_DiaryPhase_stuff_TypeInfo";

export class _DiaryPhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _DiaryPhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryPhase_stuff_TypeInfo;
    }

}

export default _DiaryPhase_stuff_Validators;