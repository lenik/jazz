import TestQuestionTag from "./TestQuestionTag";
import TestQuestionTagValidators from "./TestQuestionTagValidators";
import _TestQuestionTag_stuff_TypeInfo from "./_TestQuestionTag_stuff_TypeInfo";

export class TestQuestionTagTypeInfo extends _TestQuestionTag_stuff_TypeInfo {

    readonly validators = new TestQuestionTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TestQuestionTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionTagTypeInfo();

}

export default TestQuestionTagTypeInfo;
