import _VAppRequestApi_stuff from "./_VAppRequestApi_stuff";
import { _VAppRequestApi_stuff_Type } from "./_VAppRequestApi_stuff_Type";

export class VAppRequestApi extends _VAppRequestApi_stuff {
    static TYPE = new _VAppRequestApi_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequestApi;
