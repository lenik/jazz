import TestQuestion from "./TestQuestion";
import TestQuestionValidators from "./TestQuestionValidators";
import _TestQuestion_stuff_TypeInfo from "./_TestQuestion_stuff_TypeInfo";

export class TestQuestionTypeInfo extends _TestQuestion_stuff_TypeInfo {

    readonly validators = new TestQuestionValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestion"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TestQuestion();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionTypeInfo();

}

export default TestQuestionTypeInfo;
