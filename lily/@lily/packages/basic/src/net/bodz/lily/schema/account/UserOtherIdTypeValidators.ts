import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type UserOtherIdTypeTypeInfo from "./UserOtherIdTypeTypeInfo";
import _UserOtherIdType_stuff_Validators from "./_UserOtherIdType_stuff_Validators";

export class UserOtherIdTypeValidators extends _UserOtherIdType_stuff_Validators {

    constructor(type: UserOtherIdTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserOtherIdTypeTypeInfo;
    }

}

export default UserOtherIdTypeValidators;
