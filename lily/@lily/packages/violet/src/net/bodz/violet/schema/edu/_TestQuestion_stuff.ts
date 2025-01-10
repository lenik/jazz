import type { int, long } from "skel01-core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type Course from "./Course";
import _TestQuestion_stuff_TypeInfo from "./_TestQuestion_stuff_TypeInfo";

export class _TestQuestion_stuff extends CoMessage<long> {

    static _typeInfo: _TestQuestion_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestQuestion_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    favCount: int;
    voteCount: int;
    hateCount: int;
    pos: int;
    answer: string;

    course: Course;
    courseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TestQuestion_stuff;
