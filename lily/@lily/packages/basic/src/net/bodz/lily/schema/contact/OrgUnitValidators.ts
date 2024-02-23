import { ValidateResult } from "@skeljs/core/src/ui/types";

import { Contact } from "./Contact";
import { PersonRole } from "./PersonRole";
import _OrgUnit_stuff_Validators from "./_OrgUnit_stuff_Validators";

export class OrgUnitValidators extends _OrgUnit_stuff_Validators {
    validateContact(val: Contact) {
    }
    validateStaff(val: PersonRole[]) {
    }

}

export default OrgUnitValidators;
