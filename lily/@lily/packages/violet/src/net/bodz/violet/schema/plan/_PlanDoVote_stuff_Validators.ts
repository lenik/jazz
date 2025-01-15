import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import VoteRecordValidators from "lily-basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type PlanDo from "./PlanDo";
import type _PlanDoVote_stuff_TypeInfo from "./_PlanDoVote_stuff_TypeInfo";

export class _PlanDoVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _PlanDoVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanDoVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: PlanDo) {
    }

}

export default _PlanDoVote_stuff_Validators;
