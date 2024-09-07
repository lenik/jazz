#\import lily.security

column-property {
    odr:            orderId
    ou:             orgUnitId
    saleodr:        salesOrderId
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        trancat
    net.bodz.lily.concrete.CoPhase: \
        tranphase
    net.bodz.lily.concrete.CoMessage: \
        tranodr
    net.bodz.lily.concrete.CoEvent: \
        tranodrl
}

table-name {
    trancat:            net.bodz.violet.schema.tran.TransportCategory
    tranphase:          net.bodz.violet.schema.tran.TransportPhase
    tranodr:            net.bodz.violet.schema.tran.TransportOrder
    tranodrl:           net.bodz.violet.schema.tran.TransportOrderItem
}

table tranodr {
}

table tranodrl {
}
