import _ZoneCategory_stuff from "./_ZoneCategory_stuff";
import { _ZoneCategory_stuffTypeInfo } from "./_ZoneCategory_stuffTypeInfo";

export class ZoneCategory extends _ZoneCategory_stuff {
    static TYPE = new _ZoneCategory_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ZoneCategory;
