import FabTaskItemTypeInfo from "./FabTaskItemTypeInfo";
import _FabTaskItem_stuff from "./_FabTaskItem_stuff";

export class FabTaskItem extends _FabTaskItem_stuff {

    static _typeInfo: FabTaskItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTaskItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabTaskItem;
