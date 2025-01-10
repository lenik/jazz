import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type TestPaperTypeInfo from "./TestPaperTypeInfo";
import _TestPaper_stuff_Validators from "./_TestPaper_stuff_Validators";

export class TestPaperValidators extends _TestPaper_stuff_Validators {

    constructor(type: TestPaperTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestPaperTypeInfo;
    }

}

export default TestPaperValidators;
