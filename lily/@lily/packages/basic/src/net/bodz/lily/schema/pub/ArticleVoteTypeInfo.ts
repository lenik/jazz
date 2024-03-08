import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class ArticleVoteTypeInfo extends _ArticleVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleVote"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleVoteValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleVoteTypeInfo;
