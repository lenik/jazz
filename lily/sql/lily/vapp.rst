#\import lily.security

class-map {
    net.bodz.lily.concrete.CoCategory: \
        vappcat
    net.bodz.lily.concrete.CoImaged: \
        apitype,
        vapi,
        vapp,
    net.bodz.lily.concrete.CoMessage: \
        vappreq
}

table-name {
    apitype:            net.bodz.lily.schema.vapp.ApiType
    vapi:               net.bodz.lily.schema.vapp.VApi
    vapi_credit:        net.bodz.lily.schema.vapp.VApiCredit
    vapi_log:           net.bodz.lily.schema.vapp.VApiLog
    vapp:               net.bodz.lily.schema.vapp.VApp
    vappcat:            net.bodz.lily.schema.vapp.VAppCategory
    vappreq:            net.bodz.lily.schema.vapp.VAppRequest
    vappreq_api:        net.bodz.lily.schema.vapp.VAppRequestApi
}
