#\import lily.security

column-property {
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        diarycat, \
        plancat
    net.bodz.lily.concrete.CoParameter: \
        diaryparm, \
        planparm, \
        plandoparm
    net.bodz.lily.concrete.CoPhase: \
        diaryphase, \
        planphase
    net.bodz.lily.concrete.CoTag: \
        diarytag, \
        plantag, \
        plandotag
    net.bodz.lily.concrete.FavRecord: \
        plan_fav
    net.bodz.lily.concrete.VoteRecord: \
        diary_vote, \
        diaryrev_vote, \
        plan_vote, \
        plando_vote
    net.bodz.lily.concrete.CoMessage: \
        diary, \
        diaryrev, \
        plan, \
        plando
}

table-name {
    diary:              net.bodz.violet.schema.plan.Diary
    diarycat:           net.bodz.violet.schema.plan.DiaryCategory
    diaryparm:          net.bodz.violet.schema.plan.DiaryParameter
    diaryphase:         net.bodz.violet.schema.plan.DiaryPhase
    diarytag:           net.bodz.violet.schema.plan.DiaryTag
    diary_party:        net.bodz.violet.schema.plan.DiaryParty
    diary_vote:         net.bodz.violet.schema.plan.DiaryVote

    diaryrev:           net.bodz.violet.schema.plan.DiaryReview
    diaryrev_vote:      net.bodz.violet.schema.plan.DiaryReviewVote
    
    plan:               net.bodz.violet.schema.plan.Plan
    plancat:            net.bodz.violet.schema.plan.PlanCategory
    planparm:           net.bodz.violet.schema.plan.PlanParameter
    planphase:          net.bodz.violet.schema.plan.PlanPhase
    plantag:            net.bodz.violet.schema.plan.PlanTag
    plan_fav:           net.bodz.violet.schema.plan.PlanFav
    plan_party:         net.bodz.violet.schema.plan.PlanParty
    plan_vote:          net.bodz.violet.schema.plan.PlanVote
    
    plando:             net.bodz.violet.schema.plan.PlanDo
    plandoparm:         net.bodz.violet.schema.plan.PlanDoParameter
    plandotag:          net.bodz.violet.schema.plan.PlanDoTag
    plando_vote:        net.bodz.violet.schema.plan.PlanDoVote
}

table diary {
}
