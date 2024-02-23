import { Group } from "./Group";
import { User } from "./User";
import _Group_stuff from "./_Group_stuff";
import { _Group_stuffTypeInfo } from "./_Group_stuffTypeInfo";

export class Group extends _Group_stuff {
    static TYPE = new _Group_stuffTypeInfo();

    children?: Group[]
    users?: User[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Group;
