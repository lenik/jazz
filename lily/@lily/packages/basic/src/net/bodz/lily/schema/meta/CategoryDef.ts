import CategoryDefTypeInfo from "./CategoryDefTypeInfo";
import _CategoryDef_stuff from "./_CategoryDef_stuff";

export class CategoryDef extends _CategoryDef_stuff<CategoryDef> {

    static _typeInfo: CategoryDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CategoryDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CategoryDef;
