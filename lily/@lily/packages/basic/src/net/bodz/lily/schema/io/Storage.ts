import StorageTypeInfo from "./StorageTypeInfo";
import _Storage_stuff from "./_Storage_stuff";

export class Storage extends _Storage_stuff {
    static TYPE = new StorageTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Storage;
