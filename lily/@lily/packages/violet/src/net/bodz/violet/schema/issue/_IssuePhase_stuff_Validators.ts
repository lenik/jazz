import { ValidateResult } from "skel01-core/src/ui/types";
import CoPhaseValidators from "lily-basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _IssuePhase_stuff_TypeInfo from "./_IssuePhase_stuff_TypeInfo";

export class _IssuePhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _IssuePhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _IssuePhase_stuff_TypeInfo;
    }

}

export default _IssuePhase_stuff_Validators;
