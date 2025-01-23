import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type VAppCategory from "./VAppCategory";
import type VAppRequest from "./VAppRequest";
import type _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class _VApp_stuff_Validators extends IdEntityValidators {

    constructor(type: _VApp_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApp_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateProperties(val: JsonVariant) {
    }

    validateFiles(val: JsonVariant) {
    }

    validateSecret(val: string) {
    }

    validateCategory(val: VAppCategory) {
    }

    validateReq(val: VAppRequest) {
    }

}

export default _VApp_stuff_Validators;
