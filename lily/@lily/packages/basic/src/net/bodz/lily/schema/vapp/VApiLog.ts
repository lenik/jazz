import VApiLogTypeInfo from "./VApiLogTypeInfo";
import _VApiLog_stuff from "./_VApiLog_stuff";

export class VApiLog extends _VApiLog_stuff {
    static TYPE = new VApiLogTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiLog;
