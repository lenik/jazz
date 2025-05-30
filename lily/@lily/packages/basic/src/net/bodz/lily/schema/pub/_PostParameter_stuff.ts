import type { double, int, long } from "skel01-core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type Post from "./Post";
import type PostParameterType from "./PostParameterType";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class _PostParameter_stuff extends IdEntity<int> {

    static _typeInfo: _PostParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

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
