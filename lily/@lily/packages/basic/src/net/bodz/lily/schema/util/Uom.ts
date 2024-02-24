import UomTypeInfo from "./UomTypeInfo";
import _Uom_stuff from "./_Uom_stuff";

export class Uom extends _Uom_stuff {
    static TYPE = new UomTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Uom;
