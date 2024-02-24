import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class GroupTypeTypeInfo extends _GroupType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.GroupType"
    icon = "fa-tag"
    description = "Group Type"

    validators = new GroupTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default GroupTypeTypeInfo;
