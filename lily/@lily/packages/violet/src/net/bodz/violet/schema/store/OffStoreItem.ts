import OffStoreItemTypeInfo from "./OffStoreItemTypeInfo";
import _OffStoreItem_stuff from "./_OffStoreItem_stuff";

export class OffStoreItem extends _OffStoreItem_stuff {

    static _typeInfo: OffStoreItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = OffStoreItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default OffStoreItem;
