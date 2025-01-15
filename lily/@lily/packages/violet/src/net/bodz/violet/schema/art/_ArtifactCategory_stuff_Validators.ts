import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoCategoryValidators from "lily-basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _ArtifactCategory_stuff_TypeInfo from "./_ArtifactCategory_stuff_TypeInfo";

export class _ArtifactCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _ArtifactCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactCategory_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateSkufmt(val: string) {
    }

    validateSkufmtfull(val: string) {
    }

    validateBarfmt(val: string) {
    }

    validateBarfmtfull(val: string) {
    }

    validateBatchfmt(val: string) {
    }

    validateSerialfmt(val: string) {
    }

    validateCount(val: int) {
    }

}

export default _ArtifactCategory_stuff_Validators;
