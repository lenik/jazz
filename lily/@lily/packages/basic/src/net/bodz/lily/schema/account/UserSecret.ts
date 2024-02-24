import UserSecretTypeInfo from "./UserSecretTypeInfo";
import _UserSecret_stuff from "./_UserSecret_stuff";

export class UserSecret extends _UserSecret_stuff {
    static TYPE = new UserSecretTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserSecret;
