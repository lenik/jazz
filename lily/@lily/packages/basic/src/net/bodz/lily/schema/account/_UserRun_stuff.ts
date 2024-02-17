
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Object } from "../../../../../java/lang/Object";
import type { User } from "./User";
import type { _UserRun_stuff_Type } from "./_UserRun_stuff_Type";

export class _UserRun_stuff extends CoEntity<Integer> {
    static TYPE = new _UserRun_stuff_Type();

    score: int;
    lastLoginTime?: Date;
    lastLoginIP?: Object;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}
