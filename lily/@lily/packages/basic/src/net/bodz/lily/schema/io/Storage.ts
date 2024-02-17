
import { * as validators } from "./PersonValidators";
import type { _Storage_stuff } from "./_Storage_stuff";

export class Storage extends _Storage_stuff {
    static TYPE = new StorageType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
