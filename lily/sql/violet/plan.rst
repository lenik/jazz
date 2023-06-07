#\import lily.security

column-property {
}

class-map {
    net.bodz.lily.template.CoCategory: \
        diarycat, \
        plancat
    net.bodz.lily.template.CoParameter: \
        diaryparm, \
        planparm, \
        plandoparm
    net.bodz.lily.template.CoPhase: \
        diaryphase, \
        planphase
    net.bodz.lily.template.CoTag: \
        diarytag, \
        plantag, \
        plandotag
    net.bodz.lily.template.FavRecord: \
        plan_fav
    net.bodz.lily.template.VoteRecord: \
        diary_vote, \
        diaryrev_vote, \
        plan_vote, \
        plando_vote
    net.bodz.lily.t.base.CoMessage: \
        diary, \
        diaryrev, \
        plan, \
        plando
}

table-name {
    diary:              net.bodz.violet.plan.Diary
    diarycat:           net.bodz.violet.plan.DiaryCategory
    diaryparm:          net.bodz.violet.plan.DiaryParameter
    diaryphase:         net.bodz.violet.plan.DiaryPhase
    diarytag:           net.bodz.violet.plan.DiaryTag
    diary_party:        net.bodz.violet.plan.DiaryParty
    diary_vote:         net.bodz.violet.plan.DiaryVote

    diaryrev:           net.bodz.violet.plan.DiaryReview
    diaryrev_vote:      net.bodz.violet.plan.DiaryReviewVote
    
    plan:               net.bodz.violet.plan.Plan
    plancat:            net.bodz.violet.plan.PlanCategory
    planparm:           net.bodz.violet.plan.PlanParameter
    planphase:          net.bodz.violet.plan.PlanPhase
    plantag:            net.bodz.violet.plan.PlanTag
    plan_fav:           net.bodz.violet.plan.PlanFav
    plan_party:         net.bodz.violet.plan.PlanParty
    plan_vote:          net.bodz.violet.plan.PlanVote
    
    plando:             net.bodz.violet.plan.PlanDo
    plandoparm:         net.bodz.violet.plan.PlanDoParameter
    plandotag:          net.bodz.violet.plan.PlanDoTag
    plando_vote:        net.bodz.violet.plan.PlanDoVote
}

table diary {
}
