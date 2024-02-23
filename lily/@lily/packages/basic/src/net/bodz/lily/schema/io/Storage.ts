import _Storage_stuff from "./_Storage_stuff";
import { _Storage_stuff_Type } from "./_Storage_stuff_Type";

export class Storage extends _Storage_stuff {
    static TYPE = new _Storage_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Storage;
