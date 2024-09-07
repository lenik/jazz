#\import lily.security

column-property {
    q:                  questionId
    ans:                answer
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        coursecat, \
        coursekitcat
    net.bodz.lily.concrete.CoImaged: \
        course, \
        coursekit, \
        testpaperl
    net.bodz.lily.concrete.CoMessage: \
        testq, \
        testans, \
        testpaper, \
        testapply
    net.bodz.lily.concrete.CoTag: \
        coursetag, \
        coursekittag, \
        testqtag
    net.bodz.lily.concrete.FavRecord: \
        course_fav, \
        coursekit_fav, \
        testq_fav
    net.bodz.lily.concrete.VoteRecord: \
        testq_vote, \
        testq_msg_vote
}

table-name {
    course:             net.bodz.violet.schema.edu.Course
    coursecat:          net.bodz.violet.schema.edu.CourseCategory
    coursetag:          net.bodz.violet.schema.edu.CourseTag
    course_fav:         net.bodz.violet.schema.edu.CourseFav

    coursekit:          net.bodz.violet.schema.edu.CourseKit
    coursekitcat:       net.bodz.violet.schema.edu.CourseKitCategory
    coursekittag:       net.bodz.violet.schema.edu.CourseKitTag
    coursekit_fav:      net.bodz.violet.schema.edu.CourseKitFav

    testq:              net.bodz.violet.schema.edu.TestQuestion
    testans:            net.bodz.violet.schema.edu.TestAnswer
    testqtag:           net.bodz.violet.schema.edu.TestQuestionTag
    testq_fav:          net.bodz.violet.schema.edu.TestQuestionFav
    testq_vote:         net.bodz.violet.schema.edu.TestQuestionVote
    testq_msg:          net.bodz.violet.schema.edu.TestQuestionTalk
    testq_msg_vote:     net.bodz.violet.schema.edu.TestQuestionTalkVote
    
    testpaper:          net.bodz.violet.schema.edu.TestPaper
    testpaperl:         net.bodz.violet.schema.edu.TestPaperItem

    testapply:          net.bodz.violet.schema.edu.TestApply
    testapplyl:         net.bodz.violet.schema.edu.TestApplyItem
}

table course {
}
