import PersonRoleTypeInfo from "./PersonRoleTypeInfo";
import _PersonRole_stuff from "./_PersonRole_stuff";

export class PersonRole extends _PersonRole_stuff {
    static TYPE = new PersonRoleTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonRole;
