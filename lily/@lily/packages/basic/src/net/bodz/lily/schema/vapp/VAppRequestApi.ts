
import { * as validators } from "./PersonValidators";
import type { _VAppRequestApi_stuff } from "./_VAppRequestApi_stuff";

export class VAppRequestApi extends _VAppRequestApi_stuff {
    static TYPE = new VAppRequestApiType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
