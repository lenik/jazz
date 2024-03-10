import FabTaskTypeInfo from "./FabTaskTypeInfo";
import _FabTask_stuff from "./_FabTask_stuff";

export class FabTask extends _FabTask_stuff {

    static _typeInfo: FabTaskTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTaskTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabTask;
