import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
