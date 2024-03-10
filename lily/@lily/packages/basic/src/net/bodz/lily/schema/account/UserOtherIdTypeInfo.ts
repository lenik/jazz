import UserOtherIdValidators from "./UserOtherIdValidators";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class UserOtherIdTypeInfo extends _UserOtherId_stuff_TypeInfo {

    readonly validators = new UserOtherIdValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserOtherId"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Open ID"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserOtherIdTypeInfo();

}

export default UserOtherIdTypeInfo;
