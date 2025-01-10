import type { int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type DiaryCategory from "./DiaryCategory";
import type DiaryPhase from "./DiaryPhase";
import type _Diary_stuff_TypeInfo from "./_Diary_stuff_TypeInfo";

export class _Diary_stuff_Validators extends CoMessageValidators {

    constructor(type: _Diary_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Diary_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateValue(val: int) {
    }

    validatePhase(val: DiaryPhase) {
    }

    validateCategory(val: DiaryCategory) {
    }

}

export default _Diary_stuff_Validators;
