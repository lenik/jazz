import { ValidateResult } from "skel01-core/src/ui/types";

import CoPrincipalValidators from "../../concrete/CoPrincipalValidators";
import type Person from "../contact/Person";
import type Group from "./Group";
import type User from "./User";
import type UserType from "./UserType";
import type _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class _User_stuff_Validators extends CoPrincipalValidators {

    constructor(type: _User_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _User_stuff_TypeInfo;
    }

    validatePerson(val: Person) {
    }

    validatePrimaryGroup(val: Group) {
    }

    validateReferer(val: User) {
    }

    validateType(val: UserType) {
    }

}

export default _User_stuff_Validators;
