import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class GroupTypeTypeInfo extends _GroupType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.GroupType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Group Type"; }

    validators = new GroupTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default GroupTypeTypeInfo;
