import type { double, integer, long } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type Post from "./Post";
import type PostParameterType from "./PostParameterType";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class _PostParameter_stuff extends CoEntity<integer> {
    static TYPE = new _PostParameter_stuff_TypeInfo();

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
