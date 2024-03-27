import UserType from "./UserType";
import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class UserTypeTypeInfo extends _UserType_stuff_TypeInfo {

    readonly validators = new UserTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserType"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Type"; }

    override create() {
        return new UserType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserTypeTypeInfo();

}

export default UserTypeTypeInfo;
