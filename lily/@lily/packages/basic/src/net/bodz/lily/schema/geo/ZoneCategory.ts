
import { * as validators } from "./PersonValidators";
import type { _ZoneCategory_stuff } from "./_ZoneCategory_stuff";

export class ZoneCategory extends _ZoneCategory_stuff {
    static TYPE = new ZoneCategoryType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
