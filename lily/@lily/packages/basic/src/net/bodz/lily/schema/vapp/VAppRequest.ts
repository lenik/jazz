import _VAppRequest_stuff from "./_VAppRequest_stuff";
import { _VAppRequest_stuff_Type } from "./_VAppRequest_stuff_Type";

export class VAppRequest extends _VAppRequest_stuff {
    static TYPE = new _VAppRequest_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequest;
