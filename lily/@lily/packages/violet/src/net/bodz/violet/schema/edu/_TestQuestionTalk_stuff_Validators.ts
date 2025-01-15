import type { int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "lily-basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type User from "lily-basic/src/net/bodz/lily/schema/account/User";
import type FormDef from "lily-basic/src/net/bodz/lily/schema/meta/FormDef";

import type TestQuestion from "./TestQuestion";
import type TestQuestionTalk from "./TestQuestionTalk";
import type _TestQuestionTalk_stuff_TypeInfo from "./_TestQuestionTalk_stuff_TypeInfo";

export class _TestQuestionTalk_stuff_Validators extends IdEntityValidators {

    constructor(type: _TestQuestionTalk_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestionTalk_stuff_TypeInfo;
    }

    validateBeginTime(val: OffsetDateTime) {
    }

    validateEndTime(val: OffsetDateTime) {
    }

    validateYear(val: int) {
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

    validateFormArguments(val: string) {
    }

    validateForm(val: FormDef) {
    }

    validateOp(val: User) {
    }

    validateParent(val: TestQuestionTalk) {
    }

    validateQuestion(val: TestQuestion) {
    }

}

export default _TestQuestionTalk_stuff_Validators;
