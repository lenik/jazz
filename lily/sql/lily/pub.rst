#\import lily.security

class-map {
    net.bodz.lily.t.base.CoMessage: \
        article, \
        post
    net.bodz.lily.template.BackrefRecord: \
        article_backref, \
        post_backref
    net.bodz.lily.template.FavRecord: \
        article_fav, \
        post_fav
    net.bodz.lily.template.VoteRecord: \
        article_vote, \
        post_vote, \
        article_msg_vote, \
        post_msg_vote
    net.bodz.lily.template.CoTalk: \
        article_msg, \
        post_msg
    net.bodz.lily.template.CoParameter: \
        articleparm, \
        postparm
    net.bodz.lily.template.CoTag: \
        articletag, \
        posttag
}

table-name {
    article:            net.bodz.lily.pub.Article
    article_backref:    net.bodz.lily.pub.ArticleBackref
    articlecat:         net.bodz.lily.pub.ArticleCategory
    articleparm:        net.bodz.lily.pub.ArticleParameterType
    article_parm:       net.bodz.lily.pub.ArticleParameter
    articletag:         net.bodz.lily.pub.ArticleTagType
    article_tag:        net.bodz.lily.pub.ArticleTag
    article_fav:        net.bodz.lily.pub.ArticleFav
    article_vote:       net.bodz.lily.pub.ArticleVote
    article_msg:        net.bodz.lily.pub.ArticleTalk
    article_msg_vote:   net.bodz.lily.pub.ArticleTalkVote
    post:               net.bodz.lily.pub.Post
    post_backref:       net.bodz.lily.pub.PostBackref
    postcat:            net.bodz.lily.pub.PostCategory
    post_fav:           net.bodz.lily.pub.PostFav
    postparm:           net.bodz.lily.pub.PostParameterType
    post_parm:          net.bodz.lily.pub.PostParameter
    posttag:            net.bodz.lily.pub.PostTagType
    post_tag:           net.bodz.lily.pub.PostTag
    post_vote:          net.bodz.lily.pub.PostVote
    post_msg:           net.bodz.lily.pub.PostTalk
    post_msg_vote:      net.bodz.lily.pub.PostTalkVote
}
