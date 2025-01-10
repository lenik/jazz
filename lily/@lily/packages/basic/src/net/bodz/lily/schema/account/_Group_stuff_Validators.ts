import { ValidateResult } from "skel01-core/src/ui/types";

import CoPrincipalValidators from "../../concrete/CoPrincipalValidators";
import type Group from "./Group";
import type GroupType from "./GroupType";
import type _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class _Group_stuff_Validators extends CoPrincipalValidators {

    constructor(type: _Group_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Group_stuff_TypeInfo;
    }

    validateParent(val: Group) {
    }

    validateType(val: GroupType) {
    }

}

export default _Group_stuff_Validators;
