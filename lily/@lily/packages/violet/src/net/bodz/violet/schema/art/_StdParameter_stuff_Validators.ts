import { ValidateResult } from "skel01-core/src/ui/types";
import CoParameterValidators from "lily-basic/src/net/bodz/lily/concrete/CoParameterValidators";

import type _StdParameter_stuff_TypeInfo from "./_StdParameter_stuff_TypeInfo";

export class _StdParameter_stuff_Validators extends CoParameterValidators {

    constructor(type: _StdParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _StdParameter_stuff_TypeInfo;
    }

}

export default _StdParameter_stuff_Validators;
