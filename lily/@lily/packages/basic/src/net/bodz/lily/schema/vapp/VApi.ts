import _VApi_stuff from "./_VApi_stuff";
import { _VApi_stuffTypeInfo } from "./_VApi_stuffTypeInfo";

export class VApi extends _VApi_stuff {
    static TYPE = new _VApi_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApi;
