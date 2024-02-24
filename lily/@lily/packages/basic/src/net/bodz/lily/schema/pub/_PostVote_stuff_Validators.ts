import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import VoteRecordValidators from "../../concrete/VoteRecordValidators";
import type Post from "./Post";
import type _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class _PostVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _PostVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostVote_stuff_TypeInfo;
    }

    validateVoteScore(val: integer) {
    }

    validateParent(val: Post) {
    }

}

export default _PostVote_stuff_Validators;
