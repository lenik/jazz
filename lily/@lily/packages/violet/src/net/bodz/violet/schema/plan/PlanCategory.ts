import PlanCategoryTypeInfo from "./PlanCategoryTypeInfo";
import _PlanCategory_stuff from "./_PlanCategory_stuff";

export class PlanCategory extends _PlanCategory_stuff<PlanCategory> {

    static _typeInfo: PlanCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanCategory;
