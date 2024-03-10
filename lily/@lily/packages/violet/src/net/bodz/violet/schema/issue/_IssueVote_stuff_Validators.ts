import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type Issue from "./Issue";
import type _IssueVote_stuff_TypeInfo from "./_IssueVote_stuff_TypeInfo";

export class _IssueVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _IssueVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _IssueVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: Issue) {
    }

}

export default _IssueVote_stuff_Validators;
