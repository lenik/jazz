import TestPaperItemValidators from "./TestPaperItemValidators";
import _TestPaperItem_stuff_TypeInfo from "./_TestPaperItem_stuff_TypeInfo";

export class TestPaperItemTypeInfo extends _TestPaperItem_stuff_TypeInfo {

    readonly validators = new TestPaperItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestPaperItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestPaperItemTypeInfo();

}

export default TestPaperItemTypeInfo;
