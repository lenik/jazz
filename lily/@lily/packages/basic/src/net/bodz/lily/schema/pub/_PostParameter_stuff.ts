import type { double, integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Post from "./Post";
import PostParameterType from "./PostParameterType";
import _PostParameter_stuff_Type from "./_PostParameter_stuff_Type";

export class _PostParameter_stuff extends CoEntity<integer> {
    static TYPE = new _PostParameter_stuff_Type();

    id: integer;
    ival?: integer;
    fval?: double;
    sval?: string;

    post: Post;
    postId: long;

    parameter: PostParameterType;
    parameterId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PostParameter_stuff;
