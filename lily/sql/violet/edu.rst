#\import lily.security

column-property {
    q:                  question
    ans:                answer
}

class-map {
    net.bodz.lily.template.CoCategory: \
        coursecat, \
        coursekitcat
    net.bodz.lily.template.CoTag: \
        coursetag, \
        coursekittag, \
        testqtag
    net.bodz.lily.template.FavRecord: \
        course_fav, \
        coursekit_fav, \
        testq_fav
    net.bodz.lily.template.VoteRecord: \
        testq_vote, \
        testq_msg_vote
    net.bodz.lily.t.base.CoMessage: \
        testq
}

table-name {
    course:             net.bodz.violet.edu.Course
    coursecat:          net.bodz.violet.edu.CourseCategory
    coursetag:          net.bodz.violet.edu.CourseTag
    course_fav:         net.bodz.violet.edu.CourseFav

    coursekit:          net.bodz.violet.edu.CourseKit
    coursekitcat:       net.bodz.violet.edu.CourseKitCategory
    coursekittag:       net.bodz.violet.edu.CourseKitTag
    coursekit_fav:      net.bodz.violet.edu.CourseKitFav

    testq:              net.bodz.violet.edu.TestQuestion
    testans:            net.bodz.violet.edu.TestAnswer
    testqtag:           net.bodz.violet.edu.TestQuestionTag
    testq_fav:          net.bodz.violet.edu.TestQuestionFav
    testq_vote:         net.bodz.violet.edu.TestQuestionVote
    testq_msg:          net.bodz.violet.edu.TestQuestionTalk
    testq_msg_vote:     net.bodz.violet.edu.TestQuestionTalkVote
    
    testpaper:          net.bodz.violet.edu.TestPaper
    testpaperl:         net.bodz.violet.edu.TestPaperItem

    testapply:          net.bodz.violet.edu.TestApply
    testapplyl:         net.bodz.violet.edu.TestApplyItem
}

table course {
}
