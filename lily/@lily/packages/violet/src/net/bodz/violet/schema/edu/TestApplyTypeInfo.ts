import TestApply from "./TestApply";
import TestApplyValidators from "./TestApplyValidators";
import _TestApply_stuff_TypeInfo from "./_TestApply_stuff_TypeInfo";

export class TestApplyTypeInfo extends _TestApply_stuff_TypeInfo {

    readonly validators = new TestApplyValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestApply"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TestApply();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestApplyTypeInfo();

}

export default TestApplyTypeInfo;

export const TestApply_TYPE = TestApplyTypeInfo.INSTANCE;
