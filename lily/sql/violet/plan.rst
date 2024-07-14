#\import lily.security

column-property {
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        diarycat, \
        plancat
    net.bodz.lily.concrete.CoMessage: \
        diary, \
        diaryrev, \
        plan, \
        plando
    net.bodz.lily.concrete.CoParameter: \
        diaryparm, \
        planparm, \
        plandoparm
    net.bodz.lily.concrete.CoPhase: \
        diaryphase, \
        planphase
    net.bodz.lily.concrete.CoRelation: \
        diary_parm, \
        plan_parm, \
        plando_parm
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
}

table-name {
    diary:              net.bodz.violet.schema.plan.Diary
    diarycat:           net.bodz.violet.schema.plan.DiaryCategory
    diaryparm:          net.bodz.violet.schema.plan.DiaryParameterType
    diaryphase:         net.bodz.violet.schema.plan.DiaryPhase
    diarytag:           net.bodz.violet.schema.plan.DiaryTag
    diary_parm:         net.bodz.violet.schema.plan.DiaryParam
    diary_party:        net.bodz.violet.schema.plan.DiaryParty
    diary_vote:         net.bodz.violet.schema.plan.DiaryVote

    diaryrev:           net.bodz.violet.schema.plan.DiaryReview
    diaryrev_vote:      net.bodz.violet.schema.plan.DiaryReviewVote
    
    plan:               net.bodz.violet.schema.plan.Plan
    plancat:            net.bodz.violet.schema.plan.PlanCategory
    planparm:           net.bodz.violet.schema.plan.PlanParameterType
    planphase:          net.bodz.violet.schema.plan.PlanPhase
    plantag:            net.bodz.violet.schema.plan.PlanTag
    plan_fav:           net.bodz.violet.schema.plan.PlanFav
    plan_parm:          net.bodz.violet.schema.plan.PlanParam
    plan_party:         net.bodz.violet.schema.plan.PlanParty
    plan_vote:          net.bodz.violet.schema.plan.PlanVote
    
    plando:             net.bodz.violet.schema.plan.PlanDo
    plandoparm:         net.bodz.violet.schema.plan.PlanDoParameterType
    plandotag:          net.bodz.violet.schema.plan.PlanDoTag
    plando_parm:        net.bodz.violet.schema.plan.PlanDoParam
    plando_vote:        net.bodz.violet.schema.plan.PlanDoVote
}

table diary {
}
