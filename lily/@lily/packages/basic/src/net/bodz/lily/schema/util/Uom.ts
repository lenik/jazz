import _Uom_stuff from "./_Uom_stuff";
import { _Uom_stuffTypeInfo } from "./_Uom_stuffTypeInfo";

export class Uom extends _Uom_stuff {
    static TYPE = new _Uom_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Uom;
