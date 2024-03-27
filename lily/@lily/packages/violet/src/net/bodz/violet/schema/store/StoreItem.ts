import StoreItemTypeInfo from "./StoreItemTypeInfo";
import _StoreItem_stuff from "./_StoreItem_stuff";

export class StoreItem extends _StoreItem_stuff {

    static _typeInfo: StoreItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StoreItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default StoreItem;
