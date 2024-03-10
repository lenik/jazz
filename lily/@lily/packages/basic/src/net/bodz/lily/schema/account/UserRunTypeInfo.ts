import { STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import UserRunValidators from "./UserRunValidators";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class UserRunTypeInfo extends _UserRun_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserRun"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Activity Log"; }

    validators = new UserRunValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            activeTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateActiveTime }),
            stateText: property({ type: STRING, validator: this.validators.validateStateText }),
        });
    }

    constructor() {
        super();
    }

}

export default UserRunTypeInfo;