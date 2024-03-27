import UserBadge from "./UserBadge";
import UserBadgeValidators from "./UserBadgeValidators";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class UserBadgeTypeInfo extends _UserBadge_stuff_TypeInfo {

    readonly validators = new UserBadgeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.reward.UserBadge"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new UserBadge();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserBadgeTypeInfo();

}

export default UserBadgeTypeInfo;
