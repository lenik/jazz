import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoPrincipalValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalValidators";

import { Group } from "./Group";
import { GroupType } from "./GroupType";

export class _Group_stuffValidators extends CoPrincipalValidators {
    validateParent(val: Group) {
    }

    validateType(val: GroupType) {
    }

}

export default _Group_stuffValidators;
