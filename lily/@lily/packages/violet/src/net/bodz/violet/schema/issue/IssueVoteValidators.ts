import { ValidateResult } from "skel01-core/src/ui/types";

import type IssueVoteTypeInfo from "./IssueVoteTypeInfo";
import _IssueVote_stuff_Validators from "./_IssueVote_stuff_Validators";

export class IssueVoteValidators extends _IssueVote_stuff_Validators {

    constructor(type: IssueVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueVoteTypeInfo;
    }

}

export default IssueVoteValidators;
