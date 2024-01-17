#\import lily.security

column-property {
    odr:            orderId
    ou:             orgUnitId
    saleodr:        salesOrderId
}

class-map {
    net.bodz.lily.template.CoCategory: \
        trancat
    net.bodz.lily.template.CoPhase: \
        tranphase
    net.bodz.lily.t.base.CoMessage: \
        tranodr
    net.bodz.lily.model.base.CoMomentInterval: \
        tranodrl
}

table-name {
    trancat:            net.bodz.violet.tran.TransportCategory
    tranphase:          net.bodz.violet.tran.TransportPhase
    tranodr:            net.bodz.violet.tran.TransportOrder
    tranodrl:           net.bodz.violet.tran.TransportOrderItem
}

table tranodr {
}

table tranodrl {
}
