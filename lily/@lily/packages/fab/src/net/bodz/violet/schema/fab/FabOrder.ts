import FabOrderTypeInfo from "./FabOrderTypeInfo";
import _FabOrder_stuff from "./_FabOrder_stuff";

export class FabOrder extends _FabOrder_stuff {

    static _typeInfo: FabOrderTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabOrderTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabOrder;
