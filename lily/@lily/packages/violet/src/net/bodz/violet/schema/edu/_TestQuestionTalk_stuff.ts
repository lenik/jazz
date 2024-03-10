import type { int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type TestQuestion from "./TestQuestion";
import type TestQuestionTalk from "./TestQuestionTalk";
import _TestQuestionTalk_stuff_TypeInfo from "./_TestQuestionTalk_stuff_TypeInfo";

export class _TestQuestionTalk_stuff extends CoEntity<long> {

    static _typeInfo: _TestQuestionTalk_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestQuestionTalk_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: int;
    subject: string;
    rawText?: string;
    formArguments?: string;

    form?: FormDef;
    formId?: int;

    op?: User;
    opId?: int;

    parent?: TestQuestionTalk;
    parentId?: long;

    question: TestQuestion;
    questionId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestQuestionTalk_stuff;
