import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoPrincipalValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalValidators";

import { Person } from "../contact/Person";
import { Group } from "./Group";
import { User } from "./User";
import { UserType } from "./UserType";

export class _User_stuff_Validators extends CoPrincipalValidators {
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
