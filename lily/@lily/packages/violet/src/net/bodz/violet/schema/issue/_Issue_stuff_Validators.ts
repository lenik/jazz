import type { double, int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "lily-basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type IssueCategory from "./IssueCategory";
import type IssuePhase from "./IssuePhase";
import type _Issue_stuff_TypeInfo from "./_Issue_stuff_TypeInfo";

export class _Issue_stuff_Validators extends CoMessageValidators {

    constructor(type: _Issue_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Issue_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateReadCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateNlike(val: int) {
    }

    validateValue(val: double) {
    }

    validateCategory(val: IssueCategory) {
    }

    validatePhase(val: IssuePhase) {
    }

}

export default _Issue_stuff_Validators;
