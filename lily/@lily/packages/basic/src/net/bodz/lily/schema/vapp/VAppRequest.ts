import _VAppRequest_stuff from "./_VAppRequest_stuff";
import { _VAppRequest_stuffTypeInfo } from "./_VAppRequest_stuffTypeInfo";

export class VAppRequest extends _VAppRequest_stuff {
    static TYPE = new _VAppRequest_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequest;
