import { Moment } from "moment";

import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserRunValidators from "./UserRunValidators";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

// Type Info

export class UserRunTypeInfo extends _UserRun_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserRun"
    icon = "fa-tag"
    description = "User Activity Log"

    static validators = new UserRunValidators();

    static declaredProperty: EntityPropertyMap = {
        activeTime: property({ type: "Moment", validator: this.validators.validateActiveTime }),
        stateText: property({ type: "string", validator: this.validators.validateStateText }),
    }

    constructor() {
        super();
        this.declare(UserRunTypeInfo.declaredProperty);
    }

}

export default UserRun;
