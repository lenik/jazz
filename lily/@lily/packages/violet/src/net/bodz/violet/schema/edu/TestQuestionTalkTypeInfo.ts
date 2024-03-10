import TestQuestionTalkValidators from "./TestQuestionTalkValidators";
import _TestQuestionTalk_stuff_TypeInfo from "./_TestQuestionTalk_stuff_TypeInfo";

export class TestQuestionTalkTypeInfo extends _TestQuestionTalk_stuff_TypeInfo {

    readonly validators = new TestQuestionTalkValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionTalk"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionTalkTypeInfo();

}

export default TestQuestionTalkTypeInfo;
