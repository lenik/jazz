import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class ArticleVoteTypeInfo extends _ArticleVote_stuff_TypeInfo {

    readonly validators = new ArticleVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleVoteTypeInfo();

}

export default ArticleVoteTypeInfo;
