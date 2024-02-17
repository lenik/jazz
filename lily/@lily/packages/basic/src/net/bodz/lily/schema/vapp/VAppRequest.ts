
import { * as validators } from "./PersonValidators";
import type { _VAppRequest_stuff } from "./_VAppRequest_stuff";

export class VAppRequest extends _VAppRequest_stuff {
    static TYPE = new VAppRequestType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
