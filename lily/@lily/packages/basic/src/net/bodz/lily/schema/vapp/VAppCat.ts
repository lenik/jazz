import VAppCatTypeInfo from "./VAppCatTypeInfo";
import _VAppCat_stuff from "./_VAppCat_stuff";

export class VAppCat extends _VAppCat_stuff {
    static TYPE = new VAppCatTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppCat;
