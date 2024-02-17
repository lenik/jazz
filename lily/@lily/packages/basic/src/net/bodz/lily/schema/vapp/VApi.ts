
import { * as validators } from "./PersonValidators";
import type { _VApi_stuff } from "./_VApi_stuff";

export class VApi extends _VApi_stuff {
    static TYPE = new VApiType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
