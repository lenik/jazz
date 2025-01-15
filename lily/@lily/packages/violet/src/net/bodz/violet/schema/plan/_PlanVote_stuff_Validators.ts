import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import VoteRecordValidators from "lily-basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type Plan from "./Plan";
import type _PlanVote_stuff_TypeInfo from "./_PlanVote_stuff_TypeInfo";

export class _PlanVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _PlanVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: Plan) {
    }

}

export default _PlanVote_stuff_Validators;
