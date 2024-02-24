import { ValidateResult } from "@skeljs/core/src/ui/types";

import type OrganizationTypeInfo from "./OrganizationTypeInfo";
import _Organization_stuff_Validators from "./_Organization_stuff_Validators";

export class OrganizationValidators extends _Organization_stuff_Validators {

    constructor(type: OrganizationTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as OrganizationTypeInfo;
    }

}

export default OrganizationValidators;
