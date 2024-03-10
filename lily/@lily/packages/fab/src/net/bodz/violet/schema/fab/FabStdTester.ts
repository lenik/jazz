import FabStdTesterTypeInfo from "./FabStdTesterTypeInfo";
import _FabStdTester_stuff from "./_FabStdTester_stuff";

export class FabStdTester extends _FabStdTester_stuff {

    static _typeInfo: FabStdTesterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdTesterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabStdTester;
