import CategoryDefTypeInfo from "./CategoryDefTypeInfo";
import _CategoryDef_stuff from "./_CategoryDef_stuff";

export class CategoryDef extends _CategoryDef_stuff {
    static TYPE = new CategoryDefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CategoryDef;
