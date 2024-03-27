import UserSecret from "./UserSecret";
import UserSecretValidators from "./UserSecretValidators";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class UserSecretTypeInfo extends _UserSecret_stuff_TypeInfo {

    readonly validators = new UserSecretValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserSecret"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Secret"; }

    override create() {
        return new UserSecret();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserSecretTypeInfo();

}

export default UserSecretTypeInfo;
