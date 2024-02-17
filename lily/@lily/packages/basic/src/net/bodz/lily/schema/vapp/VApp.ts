
import { * as validators } from "./PersonValidators";
import type { _VApp_stuff } from "./_VApp_stuff";

export class VApp extends _VApp_stuff {
    static TYPE = new VAppType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
