#\import lily.security

column-property {
    shopitem:           shopItemId
    sum_qty:            totalQuantity
    sum_amount:         totalAmount
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        shopcat, \
        shopitemcat, \
        couponcat, \
        shopmemcat, \
        salecat
    net.bodz.lily.concrete.CoMessage: \
        saleodr
    net.bodz.lily.concrete.CoImaged: \
        shop, \
        coupon
    net.bodz.lily.concrete.CoImagedEvent: \
        shopitem, \
        saleodrl
    net.bodz.lily.concrete.CoPhase: \
        salephase
    net.bodz.lily.concrete.CoTag: \
        shoptag
    net.bodz.lily.concrete.FavRecord: \
        shop_fav, \
        shopitem_fav
}

table-name {
    shop:               net.bodz.violet.schema.shop.Shop
    shopcat:            net.bodz.violet.schema.shop.ShopCategory
    shoptag:            net.bodz.violet.schema.shop.ShopTag
    shop_fav:           net.bodz.violet.schema.shop.ShopFav
    
    shopitem:           net.bodz.violet.schema.shop.ShopItem
    shopitemcat:        net.bodz.violet.schema.shop.ShopItemCategory
    shopitem_fav:       net.bodz.violet.schema.shop.ShopItemFav

    shopmem:            net.bodz.violet.schema.shop.Membership
    shopmemcat:         net.bodz.violet.schema.shop.MembershipCategory
    
    art_price:          net.bodz.violet.schema.shop.SellPrice
    coupon:             net.bodz.violet.schema.shop.Coupon
    couponcat:          net.bodz.violet.schema.shop.CouponCategory
    
    cart:               net.bodz.violet.schema.shop.Cart
    cartitem:           net.bodz.violet.schema.shop.CartItem
    
    salecat:            net.bodz.violet.schema.shop.SalesCategory
    salephase:          net.bodz.violet.schema.shop.SalesPhase
    saleodr:            net.bodz.violet.schema.shop.SalesOrder
    saleodrl:           net.bodz.violet.schema.shop.SalesOrderItem
}

table shop {
}

table saleodr {
    column prev {
        javaName: previousOrder
    }
}
