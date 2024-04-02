import ArticleTalkVote from "./ArticleTalkVote";
import ArticleTalkVoteValidators from "./ArticleTalkVoteValidators";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class ArticleTalkVoteTypeInfo extends _ArticleTalkVote_stuff_TypeInfo {

    readonly validators = new ArticleTalkVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTalkVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleTalkVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleTalkVoteTypeInfo();

}

export default ArticleTalkVoteTypeInfo;

export const ArticleTalkVote_TYPE = ArticleTalkVoteTypeInfo.INSTANCE;
