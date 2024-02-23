import _VAppRequestApi_stuff from "./_VAppRequestApi_stuff";
import { _VAppRequestApi_stuffTypeInfo } from "./_VAppRequestApi_stuffTypeInfo";

export class VAppRequestApi extends _VAppRequestApi_stuff {
    static TYPE = new _VAppRequestApi_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequestApi;
