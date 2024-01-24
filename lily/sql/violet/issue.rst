#\import lily.security

column-property {
    nread:              readCount
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        issuecat
    net.bodz.lily.concrete.CoParameter: \
        issueparm
    net.bodz.lily.concrete.CoPhase: \
        issuephase
    net.bodz.lily.concrete.CoTag: \
        issuetag
    net.bodz.lily.concrete.FavRecord: \
        issue_fav
    net.bodz.lily.concrete.VoteRecord: \
        issue_vote, \
        issuelog_vote
    net.bodz.lily.concrete.CoMessage: \
        issue, \
        issuelog
}

table-name {
    issue:              net.bodz.violet.schema.issue.Issue
    issuecat:           net.bodz.violet.schema.issue.IssueCategory
    issueparm:          net.bodz.violet.schema.issue.IssueParameter
    issuephase:         net.bodz.violet.schema.issue.IssuePhase
    issuetag:           net.bodz.violet.schema.issue.IssueTag
    issue_fav:          net.bodz.violet.schema.issue.IssueFav
    issue_vote:         net.bodz.violet.schema.issue.IssueVote
    
    issuelog:           net.bodz.violet.schema.issue.IssueLog
    issuelog_vote:      net.bodz.violet.schema.issue.IssueLogVote
}

table issue {
}
