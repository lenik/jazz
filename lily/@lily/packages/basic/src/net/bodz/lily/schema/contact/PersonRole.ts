import PersonRoleTypeInfo from "./PersonRoleTypeInfo";
import _PersonRole_stuff from "./_PersonRole_stuff";

export class PersonRole extends _PersonRole_stuff {
    static _typeInfo: PersonRoleTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PersonRoleTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonRole;
