import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class _ArticleCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _ArticleCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _ArticleCategory_stuff_Validators;
