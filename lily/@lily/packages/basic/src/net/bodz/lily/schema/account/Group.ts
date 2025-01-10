import type { List } from "skel01-core/src/lang/basetype";

import GroupTypeInfo from "./GroupTypeInfo";
import User from "./User";
import _Group_stuff from "./_Group_stuff";

export class Group extends _Group_stuff {

    static _typeInfo: GroupTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = GroupTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    children?: List<Group>
    users?: List<User>

    constructor(o?: any) {
        super(o);
    }
}

export default Group;
