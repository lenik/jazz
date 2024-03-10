import type { int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type TestQuestion from "./TestQuestion";
import type TestQuestionTalk from "./TestQuestionTalk";
import type _TestQuestionTalk_stuff_TypeInfo from "./_TestQuestionTalk_stuff_TypeInfo";

export class _TestQuestionTalk_stuff_Validators extends CoEntityValidators {

    constructor(type: _TestQuestionTalk_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestionTalk_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
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
