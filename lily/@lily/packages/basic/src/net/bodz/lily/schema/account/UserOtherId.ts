import UserOtherIdTypeInfo from "./UserOtherIdTypeInfo";
import _UserOtherId_stuff from "./_UserOtherId_stuff";

export class UserOtherId extends _UserOtherId_stuff {
    static TYPE = new UserOtherIdTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherId;
