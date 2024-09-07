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
    net.bodz.lily.concrete.CoEvent: \
        tranodrl
    net.bodz.violet.schema.tran.AbstractTransportOrder: \
        tranodr
}

table-name {
    trancat:            net.bodz.violet.schema.tran.TransportCategory
    tranphase:          net.bodz.violet.schema.tran.TransportPhase
    tranodr:            net.bodz.violet.schema.tran.TransportOrder
    tranodrl:           net.bodz.violet.schema.tran.TransportOrderItem
}

table tranodr {

    column src_alias {
        javaName: src.alias
    }
    column src_ctprops {
        javaName: src.properties
    }
    column src_address1  {
        javaName: src.address1
    }
    column src_address2  {
        javaName: src.address2
    }
    column src_zone {
        javaName: src.zone
    }
    column src_tel {
        javaName: src.tel
    }
    column src_telok {
        javaName: src.telValidated
    }
    column src_email {
        javaName: src.email
    }
    column src_emailok {
        javaName: src.emailValidated
    }

    column dst_alias {
        javaName: dst.alias
    }
    column dst_ctprops {
        javaName: dst.properties
    }
    column dst_address1  {
        javaName: dst.address1
    }
    column dst_address2  {
        javaName: dst.address2
    }
    column dst_zone {
        javaName: dst.zone
    }
    column dst_tel {
        javaName: dst.tel
    }
    column dst_telok {
        javaName: dst.telValidated
    }
    column dst_email {
        javaName: dst.email
    }
    column dst_emailok {
        javaName: dst.emailValidated
    }
}

table tranodrl {
}
