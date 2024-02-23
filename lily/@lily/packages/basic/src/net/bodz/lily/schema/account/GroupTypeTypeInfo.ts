import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

// Type Info

export class GroupTypeTypeInfo extends _GroupType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.GroupType"
    icon = "fa-tag"
    description = "Group Type"

    static validators = new GroupTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(GroupTypeTypeInfo.declaredProperty);
    }

}

export default GroupType;
