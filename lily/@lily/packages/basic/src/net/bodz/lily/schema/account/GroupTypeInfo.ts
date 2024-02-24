import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import Group from "./Group";
import GroupValidators from "./GroupValidators";
import User from "./User";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class GroupTypeInfo extends _Group_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.Group"
    icon = "fa-tag"
    label = "Group (Role)"
    description = "User Group"

    validators = new GroupValidators(this);

    declaredProperty: EntityPropertyMap = {
        children: property({ type: "Group[]", validator: this.validators.validateChildren }),
        users: property({ type: "User[]", validator: this.validators.validateUsers }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default GroupTypeInfo;
