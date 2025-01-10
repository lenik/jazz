import type { int } from "skel01-core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type Course from "./Course";
import _TestPaper_stuff_TypeInfo from "./_TestPaper_stuff_TypeInfo";

export class _TestPaper_stuff extends CoMessage<int> {

    static _typeInfo: _TestPaper_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestPaper_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    timeout: int;
    totalscore: int;

    course: Course;
    courseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TestPaper_stuff;
