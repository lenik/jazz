import UserOtherIdType from "./UserOtherIdType";
import UserOtherIdTypeValidators from "./UserOtherIdTypeValidators";
import _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class UserOtherIdTypeTypeInfo extends _UserOtherIdType_stuff_TypeInfo {

    readonly validators = new UserOtherIdTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserOtherIdType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Type of Open ID"; }

    override create() {
        return new UserOtherIdType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserOtherIdTypeTypeInfo();

}

export default UserOtherIdTypeTypeInfo;

export const UserOtherIdType_TYPE = UserOtherIdTypeTypeInfo.INSTANCE;
