import { ValidateResult } from "@skeljs/core/src/ui/types";

import { Contact } from "./Contact";
import { PersonRole } from "./PersonRole";
import _OrgUnit_stuffValidators from "./_OrgUnit_stuffValidators";

export class OrgUnitValidators extends _OrgUnit_stuffValidators {
    validateContact(val: Contact) {
    }
    validateStaff(val: PersonRole[]) {
    }

}

export default OrgUnitValidators;
