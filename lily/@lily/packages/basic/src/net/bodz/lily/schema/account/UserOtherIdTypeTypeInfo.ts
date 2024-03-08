import UserOtherIdTypeValidators from "./UserOtherIdTypeValidators";
import _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class UserOtherIdTypeTypeInfo extends _UserOtherIdType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserOtherIdType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Type of Open ID"; }

    validators = new UserOtherIdTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default UserOtherIdTypeTypeInfo;
