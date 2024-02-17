
import type { List } from "../../../../../java/util/List";
import { * as validators } from "./PersonValidators";
import type { _Group_stuff } from "./_Group_stuff";

export class Group extends _Group_stuff {
    static TYPE = new GroupType();

    children?: List
    users?: List

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
