import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Person } from "./Person";
import { PersonTagType } from "./PersonTagType";

export class _PersonTag_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateTag(val: PersonTagType) {
    }

    validatePerson(val: Person) {
    }

}

export default _PersonTag_stuffValidators;
