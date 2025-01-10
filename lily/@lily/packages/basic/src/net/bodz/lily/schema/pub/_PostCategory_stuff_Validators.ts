import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class _PostCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _PostCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _PostCategory_stuff_Validators;
