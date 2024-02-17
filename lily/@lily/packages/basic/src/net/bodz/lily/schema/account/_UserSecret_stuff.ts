
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { User } from "./User";
import type { _UserSecret_stuff_Type } from "./_UserSecret_stuff_Type";

export class _UserSecret_stuff extends CoEntity<Integer> {
    static TYPE = new _UserSecret_stuff_Type();

    id: int;
    password: string;
    question?: string;
    answer?: string;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}
