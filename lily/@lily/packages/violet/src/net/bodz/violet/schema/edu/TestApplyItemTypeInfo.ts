import TestApplyItem from "./TestApplyItem";
import TestApplyItemValidators from "./TestApplyItemValidators";
import _TestApplyItem_stuff_TypeInfo from "./_TestApplyItem_stuff_TypeInfo";

export class TestApplyItemTypeInfo extends _TestApplyItem_stuff_TypeInfo {

    readonly validators = new TestApplyItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestApplyItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TestApplyItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestApplyItemTypeInfo();

}

export default TestApplyItemTypeInfo;

export const TestApplyItem_TYPE = TestApplyItemTypeInfo.INSTANCE;
