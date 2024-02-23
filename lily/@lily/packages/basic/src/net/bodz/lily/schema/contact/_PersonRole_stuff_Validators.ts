import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { OrgUnit } from "./OrgUnit";
import { Organization } from "./Organization";
import { Person } from "./Person";

export class _PersonRole_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateRole(val: string) {
    }

    validateOrgUnit(val: OrgUnit) {
    }

    validatePerson(val: Person) {
    }

    validateOrg(val: Organization) {
    }

}

export default _PersonRole_stuff_Validators;
