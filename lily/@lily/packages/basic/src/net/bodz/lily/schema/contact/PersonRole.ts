import _PersonRole_stuff from "./_PersonRole_stuff";
import { _PersonRole_stuffTypeInfo } from "./_PersonRole_stuffTypeInfo";

export class PersonRole extends _PersonRole_stuff {
    static TYPE = new _PersonRole_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonRole;
