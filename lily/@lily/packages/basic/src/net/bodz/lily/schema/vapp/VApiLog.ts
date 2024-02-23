import _VApiLog_stuff from "./_VApiLog_stuff";
import { _VApiLog_stuff_Type } from "./_VApiLog_stuff_Type";

export class VApiLog extends _VApiLog_stuff {
    static TYPE = new _VApiLog_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiLog;
