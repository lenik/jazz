import { LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import GroupValidators from "./GroupValidators";
import User from "./User";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class GroupTypeInfo extends _Group_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.Group"; }
    get icon() { return "fa-tag"; }
    get label() { return "Group (Role)"; }
    get description() { return "User Group"; }

    validators = new GroupValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            children: property({ type: LIST(this), validator: this.validators.validateChildren }),
            users: property({ type: LIST(User.TYPE), validator: this.validators.validateUsers }),
        });
    }

    constructor() {
        super();
    }

}

export default GroupTypeInfo;