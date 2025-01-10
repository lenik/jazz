import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type UserTypeTypeInfo from "./UserTypeTypeInfo";
import _UserType_stuff_Validators from "./_UserType_stuff_Validators";

export class UserTypeValidators extends _UserType_stuff_Validators {

    constructor(type: UserTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserTypeTypeInfo;
    }

}

export default UserTypeValidators;
