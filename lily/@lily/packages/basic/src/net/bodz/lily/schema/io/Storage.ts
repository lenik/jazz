import StorageTypeInfo from "./StorageTypeInfo";
import _Storage_stuff from "./_Storage_stuff";

export class Storage extends _Storage_stuff {
    static _typeInfo: StorageTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new StorageTypeInfo(); 
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Storage;
