import UserBadgeValidators from "./UserBadgeValidators";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class UserBadgeTypeInfo extends _UserBadge_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.reward.UserBadge"; }
    get icon() { return "fa-tag"; }

    validators = new UserBadgeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default UserBadgeTypeInfo;
