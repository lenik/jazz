import UserTypeTypeInfo from "./UserTypeTypeInfo";
import _UserType_stuff from "./_UserType_stuff";

export class UserType extends _UserType_stuff {
    static TYPE = new UserTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserType;
