import _VApi_stuff from "./_VApi_stuff";
import { _VApi_stuff_Type } from "./_VApi_stuff_Type";

export class VApi extends _VApi_stuff {
    static TYPE = new _VApi_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApi;
