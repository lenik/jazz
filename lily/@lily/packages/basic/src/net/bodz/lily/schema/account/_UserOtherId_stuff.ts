
import type { Moment } from "moment";

import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Object } from "../../../../../java/lang/Object";
import type { User } from "./User";
import type { UserOtherIdType } from "./UserOtherIdType";
import type { _UserOtherId_stuff_Type } from "./_UserOtherId_stuff_Type";

export class _UserOtherId_stuff extends CoEntity<Integer> {
    static TYPE = new _UserOtherId_stuff_Type();

    id: int;
    beginTime?: Moment;
    endTime?: Moment;
    year: int;
    otherId: string;
    auth?: Object;

    type: UserOtherIdType;
    typeId: int;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}
