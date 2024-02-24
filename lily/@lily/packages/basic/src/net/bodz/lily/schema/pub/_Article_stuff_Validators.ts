import type { integer, long } from "@skeljs/core/src/lang/type";
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

    validateFavCount(val: integer) {
    }

    validateVoteCount(val: integer) {
    }

    validateHateCount(val: integer) {
    }

    validateMessageCount(val: integer) {
    }

    validatePlugins(val: any) {
    }

    validateCategory(val: ArticleCategory) {
    }

}

export default _Article_stuff_Validators;
