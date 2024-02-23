import _ZoneCategory_stuff from "./_ZoneCategory_stuff";
import { _ZoneCategory_stuff_Type } from "./_ZoneCategory_stuff_Type";

export class ZoneCategory extends _ZoneCategory_stuff {
    static TYPE = new _ZoneCategory_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ZoneCategory;
