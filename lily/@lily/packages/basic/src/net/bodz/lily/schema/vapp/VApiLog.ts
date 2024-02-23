import _VApiLog_stuff from "./_VApiLog_stuff";
import { _VApiLog_stuffTypeInfo } from "./_VApiLog_stuffTypeInfo";

export class VApiLog extends _VApiLog_stuff {
    static TYPE = new _VApiLog_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiLog;
