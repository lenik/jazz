import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class _VApi_stuff_Validators extends IdEntityValidators {

    constructor(type: _VApi_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApi_stuff_TypeInfo;
    }

    validateProperties(val: JsonVariant) {
    }

    validateCallback(val: string) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApi_stuff_Validators;
