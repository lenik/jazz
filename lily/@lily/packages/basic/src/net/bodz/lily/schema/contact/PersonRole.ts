import _PersonRole_stuff from "./_PersonRole_stuff";
import { _PersonRole_stuff_Type } from "./_PersonRole_stuff_Type";

export class PersonRole extends _PersonRole_stuff {
    static TYPE = new _PersonRole_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonRole;
