import StorageTypeInfo from "./StorageTypeInfo";
import _Storage_stuff from "./_Storage_stuff";

export class Storage extends _Storage_stuff {

    static _typeInfo: StorageTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StorageTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Storage;
