import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedValidators";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type _Shop_stuff_TypeInfo from "./_Shop_stuff_TypeInfo";

export class _Shop_stuff_Validators extends CoImagedValidators {

    constructor(type: _Shop_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Shop_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateHydm(val: int) {
    }

    validateSupplier(val: Person) {
    }

    validateSupplierOrg(val: Organization) {
    }

}

export default _Shop_stuff_Validators;
