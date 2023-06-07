#\import lily.security

column-property {
    nread:              readCount
}

class-map {
    net.bodz.lily.template.CoCategory: \
        issuecat
    net.bodz.lily.template.CoParameter: \
        issueparm
    net.bodz.lily.template.CoPhase: \
        issuephase
    net.bodz.lily.template.CoTag: \
        issuetag
    net.bodz.lily.template.FavRecord: \
        issue_fav
    net.bodz.lily.template.VoteRecord: \
        issue_vote, \
        issuelog_vote
    net.bodz.lily.t.base.CoMessage: \
        issue, \
        issuelog
}

table-name {
    issue:              net.bodz.violet.issue.Issue
    issuecat:           net.bodz.violet.issue.IssueCategory
    issueparm:          net.bodz.violet.issue.IssueParameter
    issuephase:         net.bodz.violet.issue.IssuePhase
    issuetag:           net.bodz.violet.issue.IssueTag
    issue_fav:          net.bodz.violet.issue.IssueFav
    issue_vote:         net.bodz.violet.issue.IssueVote
    
    issuelog:           net.bodz.violet.issue.IssueLog
    issuelog_vote:      net.bodz.violet.issue.IssueLogVote
}

table issue {
}
