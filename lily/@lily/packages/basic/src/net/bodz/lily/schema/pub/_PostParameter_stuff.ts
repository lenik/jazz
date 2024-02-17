
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer, long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Post } from "./Post";
import type { PostParameterType } from "./PostParameterType";
import type { _PostParameter_stuff_Type } from "./_PostParameter_stuff_Type";

export class _PostParameter_stuff extends CoEntity<Integer> {
    static TYPE = new _PostParameter_stuff_Type();

    id: int;
    ival?: integer;
    fval?: number;
    sval?: string;

    post: Post;
    postId: long;

    parameter: PostParameterType;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}
