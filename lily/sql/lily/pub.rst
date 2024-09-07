#\import lily.security

class-map {
    net.bodz.lily.concrete.BackrefRecord: \
        article_backref, \
        post_backref
    net.bodz.lily.concrete.CoCategory: \
        articlecat, \
        postcat
    net.bodz.lily.concrete.CoMessage: \
        article, \
        post
    net.bodz.lily.concrete.CoParameter: \
        articleparm, \
        postparm
    net.bodz.lily.concrete.CoTag: \
        articletag, \
        posttag
    net.bodz.lily.concrete.CoTalk: \
        article_msg, \
        post_msg
    net.bodz.lily.concrete.FavRecord: \
        article_fav, \
        post_fav
    net.bodz.lily.concrete.VoteRecord: \
        article_vote, \
        post_vote, \
        article_msg_vote, \
        post_msg_vote
}

table-name {
    article:            net.bodz.lily.schema.pub.Article
    article_backref:    net.bodz.lily.schema.pub.ArticleBackref
    articlecat:         net.bodz.lily.schema.pub.ArticleCategory
    articleparm:        net.bodz.lily.schema.pub.ArticleParameterType
    article_parm:       net.bodz.lily.schema.pub.ArticleParameter
    articletag:         net.bodz.lily.schema.pub.ArticleTagType
    article_tag:        net.bodz.lily.schema.pub.ArticleTag
    article_fav:        net.bodz.lily.schema.pub.ArticleFav
    article_vote:       net.bodz.lily.schema.pub.ArticleVote
    article_msg:        net.bodz.lily.schema.pub.ArticleTalk
    article_msg_vote:   net.bodz.lily.schema.pub.ArticleTalkVote
    post:               net.bodz.lily.schema.pub.Post
    post_backref:       net.bodz.lily.schema.pub.PostBackref
    postcat:            net.bodz.lily.schema.pub.PostCategory
    post_fav:           net.bodz.lily.schema.pub.PostFav
    postparm:           net.bodz.lily.schema.pub.PostParameterType
    post_parm:          net.bodz.lily.schema.pub.PostParameter
    posttag:            net.bodz.lily.schema.pub.PostTagType
    post_tag:           net.bodz.lily.schema.pub.PostTag
    post_vote:          net.bodz.lily.schema.pub.PostVote
    post_msg:           net.bodz.lily.schema.pub.PostTalk
    post_msg_vote:      net.bodz.lily.schema.pub.PostTalkVote
}
