import TestAnswerValidators from "./TestAnswerValidators";
import _TestAnswer_stuff_TypeInfo from "./_TestAnswer_stuff_TypeInfo";

export class TestAnswerTypeInfo extends _TestAnswer_stuff_TypeInfo {

    readonly validators = new TestAnswerValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestAnswer"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestAnswerTypeInfo();

}

export default TestAnswerTypeInfo;