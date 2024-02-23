import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import GroupValidators from "./GroupValidators";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

// Type Info

export class GroupTypeInfo extends _Group_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.Group"
    icon = "fa-tag"
    label = "Group (Role)"
    description = "User Group"

    static validators = new GroupValidators();

    static declaredProperty: EntityPropertyMap = {
        children: property({ type: "List", validator: this.validators.validateChildren }),
        users: property({ type: "List", validator: this.validators.validateUsers }),
    }

    constructor() {
        super();
        this.declare(GroupTypeInfo.declaredProperty);
    }

}

export default Group;
