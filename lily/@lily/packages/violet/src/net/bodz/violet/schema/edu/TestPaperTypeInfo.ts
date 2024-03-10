import TestPaperValidators from "./TestPaperValidators";
import _TestPaper_stuff_TypeInfo from "./_TestPaper_stuff_TypeInfo";

export class TestPaperTypeInfo extends _TestPaper_stuff_TypeInfo {

    readonly validators = new TestPaperValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestPaper"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestPaperTypeInfo();

}

export default TestPaperTypeInfo;
