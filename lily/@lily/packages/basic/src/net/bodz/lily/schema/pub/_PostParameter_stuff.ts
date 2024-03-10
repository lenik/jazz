import type { double, int, long } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type Post from "./Post";
import type PostParameterType from "./PostParameterType";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class _PostParameter_stuff extends CoEntity<int> {

    static _typeInfo: _PostParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: int;
    ival?: int;
    fval?: double;
    sval?: string;

    post: Post;
    postId: long;

    parameter: PostParameterType;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PostParameter_stuff;
