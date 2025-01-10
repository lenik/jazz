import { ValidateResult } from "skel01-core/src/ui/types";

import UomValidators from "./UomValidators";
import type _UomRow_stuff_TypeInfo from "./_UomRow_stuff_TypeInfo";

export class _UomRow_stuff_Validators extends UomValidators {

    constructor(type: _UomRow_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UomRow_stuff_TypeInfo;
    }

}

export default _UomRow_stuff_Validators;
