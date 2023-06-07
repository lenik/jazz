#\import lily.security

column-property {
    shopitem:           shopItem
    sum_qty:            totalQuantity
    sum_amount:         totalAmount
}

class-map {
    net.bodz.lily.template.CoCategory: \
        shopcat, \
        shopitemcat, \
        couponcat, \
        shopmemcat, \
        salecat
    net.bodz.lily.template.CoPhase: \
        salephase
    net.bodz.lily.template.CoTag: \
        shoptag
    net.bodz.lily.template.FavRecord: \
        shop_fav, \
        shopitem_fav
    net.bodz.lily.model.base.CoMomentInterval: \
        shopitem
}

table-name {
    shop:               net.bodz.violet.shop.Shop
    shopcat:            net.bodz.violet.shop.ShopCategory
    shoptag:            net.bodz.violet.shop.ShopTag
    shop_fav:           net.bodz.violet.shop.ShopFav
    
    shopitem:           net.bodz.violet.shop.ShopItem
    shopitemcat:        net.bodz.violet.shop.ShopItemCategory
    shopitem_fav:       net.bodz.violet.shop.ShopItemFav

    shopmem:            net.bodz.violet.shop.Membership
    shopmemcat:         net.bodz.violet.shop.MembershipCategory
    
    art_price:          net.bodz.violet.shop.SellPrice
    coupon:             net.bodz.violet.shop.Coupon
    couponcat:          net.bodz.violet.shop.CouponCategory
    
    cart:               net.bodz.violet.shop.Cart
    cartitem:           net.bodz.violet.shop.CartItem
    
    salecat:            net.bodz.violet.shop.SalesCategory
    salephase:          net.bodz.violet.shop.SalesPhase
    saleodr:            net.bodz.violet.shop.SalesOrder
    saleodrl:           net.bodz.violet.shop.SalesOrderItem
}

table shop {
}

table saleodr {
    column prev {
        javaName: previousOrder
    }
}
