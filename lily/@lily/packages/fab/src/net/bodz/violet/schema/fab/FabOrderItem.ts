import FabOrderItemTypeInfo from "./FabOrderItemTypeInfo";
import _FabOrderItem_stuff from "./_FabOrderItem_stuff";

export class FabOrderItem extends _FabOrderItem_stuff {

    static _typeInfo: FabOrderItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabOrderItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabOrderItem;
