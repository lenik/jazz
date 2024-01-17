#\import lily.security

column-property {
    fn:                 functionId
    nproc:              processCount
    ntask:              taskCount
    ntrack:             trackCount
    odr:                orderId
    o_label:            altLabel
    o_spec:             altSpec
    o_uom:              altUom
    ou:                 orgUnitId
    proc:               processId
    qty_actual:         actualQuantity
    qty_plan:           plannedQuantity
    qty_valid:          validQuantity
    std:                standardId
}

class-map {
    net.bodz.lily.template.CoCategory: \
        fabstdtestcat
    net.bodz.lily.t.base.CoMessage: \
        fabodr
    net.bodz.lily.model.base.CoMomentInterval: \
        fabodrl \
        fabproc \
        fabtask \
        fabtaskl \
        fabtrack
}

table-name {
    fabfn:              net.bodz.violet.fab.FabFn
    fabodr:             net.bodz.violet.fab.FabOrder
    fabodrl:            net.bodz.violet.fab.FabOrderItem
    fabproc:            net.bodz.violet.fab.FabProcess
    fabproc_sn:         net.bodz.violet.fab.FabProcessSerial
    fabstdproc:         net.bodz.violet.fab.FabStdProcess
    fabstdproc_in:      net.bodz.violet.fab.FabStdProcessInput
    fabstdtestcat:      net.bodz.violet.fab.FabStdTestCategory
    fabstdtest:         net.bodz.violet.fab.FabStdTest
    fabstdtest_parm:    net.bodz.violet.fab.FabStdTestParameter
    fabstdtester:       net.bodz.violet.fab.FabStdTester
    fabtask:            net.bodz.violet.fab.FabTask
    fabtaskl:           net.bodz.violet.fab.FabTaskItem
    fabtrack:           net.bodz.violet.fab.FabTrack
    fabtrack_op:        net.bodz.violet.fab.FabTrackParty
    fabtrack_test:      net.bodz.violet.fab.FabTrackTest
    fabtrack_test_parm: net.bodz.violet.fab.FabTrackTestParameter
}

table fabodr {
}
