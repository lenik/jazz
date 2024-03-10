import TestQuestionFavValidators from "./TestQuestionFavValidators";
import _TestQuestionFav_stuff_TypeInfo from "./_TestQuestionFav_stuff_TypeInfo";

export class TestQuestionFavTypeInfo extends _TestQuestionFav_stuff_TypeInfo {

    readonly validators = new TestQuestionFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionFavTypeInfo();

}

export default TestQuestionFavTypeInfo;
