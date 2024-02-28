import GroupTypeTypeInfo from "./GroupTypeTypeInfo";
import _GroupType_stuff from "./_GroupType_stuff";

export class GroupType extends _GroupType_stuff {
    static _typeInfo: GroupTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new GroupTypeTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default GroupType;
