import _VAppCat_stuff from "./_VAppCat_stuff";
import { _VAppCat_stuffTypeInfo } from "./_VAppCat_stuffTypeInfo";

export class VAppCat extends _VAppCat_stuff {
    static TYPE = new _VAppCat_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppCat;
