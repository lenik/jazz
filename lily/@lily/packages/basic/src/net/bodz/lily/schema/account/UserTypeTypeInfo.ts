import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class UserTypeTypeInfo extends _UserType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserType"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Type"; }

    validators = new UserTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default UserTypeTypeInfo;
