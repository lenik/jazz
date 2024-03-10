import FabProcessSerialTypeInfo from "./FabProcessSerialTypeInfo";
import _FabProcessSerial_stuff from "./_FabProcessSerial_stuff";

export class FabProcessSerial extends _FabProcessSerial_stuff {

    static _typeInfo: FabProcessSerialTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabProcessSerialTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabProcessSerial;
