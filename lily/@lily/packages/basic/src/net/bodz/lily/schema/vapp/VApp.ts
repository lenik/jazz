import _VApp_stuff from "./_VApp_stuff";
import { _VApp_stuffTypeInfo } from "./_VApp_stuffTypeInfo";

export class VApp extends _VApp_stuff {
    static TYPE = new _VApp_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApp;
