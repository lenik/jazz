import ZoneCategoryTypeInfo from "./ZoneCategoryTypeInfo";
import _ZoneCategory_stuff from "./_ZoneCategory_stuff";

export class ZoneCategory extends _ZoneCategory_stuff {
    static TYPE = new ZoneCategoryTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ZoneCategory;
