import _CategoryDef_stuff from "./_CategoryDef_stuff";
import { _CategoryDef_stuff_Type } from "./_CategoryDef_stuff_Type";

export class CategoryDef extends _CategoryDef_stuff {
    static TYPE = new _CategoryDef_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CategoryDef;
