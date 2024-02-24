import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ExternalSite from "./ExternalSite";
import type _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class _ExternalSite_stuff_Validators extends CoEntityValidators {

    constructor(type: _ExternalSite_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ExternalSite_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateDepth(val: integer) {
    }

    validateUrlfmt(val: string) {
    }

    validateBonus(val: integer) {
    }

    validateCount(val: integer) {
    }

    validateParent(val: ExternalSite) {
    }

}

export default _ExternalSite_stuff_Validators;
