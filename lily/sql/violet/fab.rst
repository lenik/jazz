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
    net.bodz.lily.concrete.CoCategory: \
        fabstdtestcat
    net.bodz.lily.concrete.CoImaged: \
        fabfn, \
        fabstdproc, \
        fabstdtest
    net.bodz.lily.concrete.CoMessage: \
        fabodr
    net.bodz.lily.concrete.CoEvent: \
        fabodrl \
        fabproc \
        fabtask \
        fabtaskl \
        fabtrack
}

table-name {
    fabfn:              net.bodz.violet.schema.fab.FabFn
    fabodr:             net.bodz.violet.schema.fab.FabOrder
    fabodrl:            net.bodz.violet.schema.fab.FabOrderItem
    fabproc:            net.bodz.violet.schema.fab.FabProcess
    fabproc_sn:         net.bodz.violet.schema.fab.FabProcessSerial
    fabstdproc:         net.bodz.violet.schema.fab.FabStdProcess
    fabstdproc_in:      net.bodz.violet.schema.fab.FabStdProcessInput
    fabstdtestcat:      net.bodz.violet.schema.fab.FabStdTestCategory
    fabstdtest:         net.bodz.violet.schema.fab.FabStdTest
    fabstdtest_parm:    net.bodz.violet.schema.fab.FabStdTestParameter
    fabstdtester:       net.bodz.violet.schema.fab.FabStdTester
    fabtask:            net.bodz.violet.schema.fab.FabTask
    fabtaskl:           net.bodz.violet.schema.fab.FabTaskItem
    fabtrack:           net.bodz.violet.schema.fab.FabTrack
    fabtrack_op:        net.bodz.violet.schema.fab.FabTrackParty
    fabtrack_test:      net.bodz.violet.schema.fab.FabTrackTest
    fabtrack_test_parm: net.bodz.violet.schema.fab.FabTrackTestParameter
}

table fabodr {
}
