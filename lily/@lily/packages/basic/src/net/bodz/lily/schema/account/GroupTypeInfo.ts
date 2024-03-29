import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import List from "../../../../../java/util/List";
import Group from "./Group";
import GroupValidators from "./GroupValidators";
import User from "./User";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class GroupTypeInfo extends _Group_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.Group"; }
    get icon() { return "fa-tag"; }
    get label() { return "Group (Role)"; }
    get description() { return "User Group"; }

    validators = new GroupValidators(this);

    declaredProperty: EntityPropertyMap = {
        children: property({ type: "List<Group>", validator: this.validators.validateChildren }),
        users: property({ type: "List<User>", validator: this.validators.validateUsers }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default GroupTypeInfo;
