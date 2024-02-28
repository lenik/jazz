import List from "../../../../../java/util/List";
import GroupTypeInfo from "./GroupTypeInfo";
import User from "./User";
import _Group_stuff from "./_Group_stuff";

export class Group extends _Group_stuff {
    static _typeInfo: GroupTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new GroupTypeInfo();
        return this._typeInfo;
    }


    children?: List<Group>
    users?: List<User>

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Group;
