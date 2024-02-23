import _VAppCat_stuff from "./_VAppCat_stuff";
import { _VAppCat_stuff_Type } from "./_VAppCat_stuff_Type";

export class VAppCat extends _VAppCat_stuff {
    static TYPE = new _VAppCat_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppCat;
