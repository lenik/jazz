import _Storage_stuff from "./_Storage_stuff";
import { _Storage_stuffTypeInfo } from "./_Storage_stuffTypeInfo";

export class Storage extends _Storage_stuff {
    static TYPE = new _Storage_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Storage;
