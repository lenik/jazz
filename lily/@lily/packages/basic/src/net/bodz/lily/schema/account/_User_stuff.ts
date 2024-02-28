import type { int } from "@skeljs/core/src/lang/basetype";

import CoPrincipal from "../../concrete/CoPrincipal";
import type Person from "../contact/Person";
import type Group from "./Group";
import type User from "./User";
import type UserType from "./UserType";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class _User_stuff extends CoPrincipal {
    static _typeInfo: _User_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _User_stuff_TypeInfo();
        return this._typeInfo;
    }


    person?: Person;
    personId?: int;

    primaryGroup: Group;
    primaryGroupId: int;

    referer?: User;
    refererId?: int;

    type: UserType;
    typeId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _User_stuff;
