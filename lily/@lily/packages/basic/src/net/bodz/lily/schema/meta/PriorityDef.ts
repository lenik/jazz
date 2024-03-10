import PriorityDefTypeInfo from "./PriorityDefTypeInfo";
import _PriorityDef_stuff from "./_PriorityDef_stuff";

export class PriorityDef extends _PriorityDef_stuff<PriorityDef> {
    static _typeInfo: PriorityDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PriorityDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PriorityDef;
