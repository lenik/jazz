import UserSecretValidators from "./UserSecretValidators";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class UserSecretTypeInfo extends _UserSecret_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserSecret"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Secret"; }

    validators = new UserSecretValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default UserSecretTypeInfo;