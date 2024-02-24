import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class _UserSecret_stuff extends CoEntity<integer> {
    static TYPE = new _UserSecret_stuff_TypeInfo();

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
