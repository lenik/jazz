import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import { OrgUnit } from "./OrgUnit";
import { Organization } from "./Organization";
import PartyValidators from "./PartyValidators";

export class _OrgUnit_stuffValidators extends PartyValidators {
    validateProperties(val: any) {
    }

    validateDepth(val: integer) {
    }

    validateOrg(val: Organization) {
    }

    validateParent(val: OrgUnit) {
    }

}

export default _OrgUnit_stuffValidators;
