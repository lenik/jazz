import _VApp_stuff from "./_VApp_stuff";
import { _VApp_stuff_Type } from "./_VApp_stuff_Type";

export class VApp extends _VApp_stuff {
    static TYPE = new _VApp_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApp;
