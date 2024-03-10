import FabStdTestTypeInfo from "./FabStdTestTypeInfo";
import _FabStdTest_stuff from "./_FabStdTest_stuff";

export class FabStdTest extends _FabStdTest_stuff {

    static _typeInfo: FabStdTestTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdTestTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabStdTest;
