import _Organization_stuff from "./_Organization_stuff";
import { _Organization_stuffTypeInfo } from "./_Organization_stuffTypeInfo";

export class Organization extends _Organization_stuff {
    static TYPE = new _Organization_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Organization;
