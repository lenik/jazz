import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoMessageValidators from "../../concrete/CoMessageValidators";
import type ArticleCategory from "./ArticleCategory";
import type _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class _Article_stuff_Validators extends CoMessageValidators {

    constructor(type: _Article_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Article_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateFavCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateHateCount(val: int) {
    }

    validateMessageCount(val: int) {
    }

    validatePlugins(val: JsonVariant) {
    }

    validateCategory(val: ArticleCategory) {
    }

}

export default _Article_stuff_Validators;
