
import { * as validators } from "./PersonValidators";
import type { _VApiLog_stuff } from "./_VApiLog_stuff";

export class VApiLog extends _VApiLog_stuff {
    static TYPE = new VApiLogType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
