import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import User from "./User";
import _UserSecret_stuff_Type from "./_UserSecret_stuff_Type";

export class _UserSecret_stuff extends CoEntity<integer> {
    static TYPE = new _UserSecret_stuff_Type();

    id: integer;
    password: string;
    question?: string;
    answer?: string;

    user: User;
    userId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserSecret_stuff;
