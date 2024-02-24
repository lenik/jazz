import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserRunValidators from "./UserRunValidators";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class UserRunTypeInfo extends _UserRun_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserRun"
    icon = "fa-tag"
    description = "User Activity Log"

    validators = new UserRunValidators(this);

    declaredProperty: EntityPropertyMap = {
        activeTime: property({ type: "ZonedDateTime", validator: this.validators.validateActiveTime }),
        stateText: property({ type: "string", validator: this.validators.validateStateText }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserRunTypeInfo;
