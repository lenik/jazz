import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_Type from "./_GroupType_stuff_Type";

// Type Info

export class GroupTypeType extends _GroupType_stuff_Type {

    name = "net.bodz.lily.schema.account.GroupType"
    icon = "fa-tag"
    description = "Group Type"

    static validators = new GroupTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(GroupTypeType.declaredProperty);
    }

}

export default GroupType;
