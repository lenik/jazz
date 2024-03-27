import PersonRoleTypeInfo from "./PersonRoleTypeInfo";
import _PersonRole_stuff from "./_PersonRole_stuff";

export class PersonRole extends _PersonRole_stuff {

    static _typeInfo: PersonRoleTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PersonRoleTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PersonRole;
