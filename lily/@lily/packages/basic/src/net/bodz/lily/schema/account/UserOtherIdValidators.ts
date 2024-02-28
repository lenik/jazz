import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type UserOtherIdTypeInfo from "./UserOtherIdTypeInfo";
import _UserOtherId_stuff_Validators from "./_UserOtherId_stuff_Validators";

export class UserOtherIdValidators extends _UserOtherId_stuff_Validators {

    constructor(type: UserOtherIdTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserOtherIdTypeInfo;
    }

}

export default UserOtherIdValidators;
