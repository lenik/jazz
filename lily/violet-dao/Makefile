include ../include/dao.mf

DBNAME = violet

typesdir = ../violet-types
daorst = violet.rst

dao-art: predao
	$(DAOGEN) \
            art artcat artparm artphase arttag \
            art_backref art_doc art_fav art_vote artpack

dao-asset: predao
	$(DAOGEN) \
            net.bodz.violet.asset.AbstractAsset \
            asset asset_group asset_org asset_ou asset_person asset_user

dao-edu: predao
	$(DAOGEN) \
            course coursecat coursetag course_fav coursekit coursekitcat coursekittag coursekit_fav \
            testq testans testqtag testq_fav testq_vote testq_msg testq_msg_vote \
            testpaper testpaperl testapply testapplyl

dao-issue: predao
	$(DAOGEN) \
            issue issuecat issueparm issuephase issuetag issue_fav issue_vote \
            issuelog issuelog_vote

dao-plan: predao
	$(DAOGEN) \
            diary diarycat diaryparm diaryphase diarytag diary_party diary_vote \
            diaryrev diaryrev_vote \
            plan plancat planparm planphase plantag plan_fav plan_party plan_vote \
            plando plandoparm plandotag plando_vote

dao-shop: predao
	$(DAOGEN) \
            shop shopcat shoptag shop_fav \
            shopitem shopitemcat shopitem_fav \
            shopmem shopmemcat \
            art_price coupon couponcat \
            cart cartitem \
            salecat salephase saleodr saleodrl

dao-store: predao
	$(DAOGEN) \
            region regioncat regiontag regionlevel \
            storecat storephase storel offstorel storeodr storeodrl

dao-tran: predao
	$(DAOGEN) \
            trancat tranphase tranodr tranodrl

dao-glob: predao
	$(DAOGEN) \
            -T violet.*

dao: predao
	$(DAOGEN) \
            art artcat artparm artphase arttag art_backref art_doc art_vote artpack \
            asset asset_group asset_org asset_ou asset_person asset_user \
            course coursecat coursetag course_fav coursekit coursekitcat coursekittag coursekit_fav \
            testq testans testqtag testq_fav testq_vote testq_msg testq_msg_vote \
            testpaper testpaperl testapply testapplyl \
            issue issuecat issueparm issuephase issuetag issue_fav issue_vote \
            issuelog issuelog_vote \
            diary diarycat diaryparm diaryphase diarytag diary_party diary_vote \
            diaryrev diaryrev_vote \
            plan plancat planparm planphase plantag plan_fav plan_party plan_vote \
            plando plandoparm plandotag plando_vote \
            shop shopcat shoptag shop_fav \
            shopitem shopitemcat shopitem_fav \
            shopmem shopmemcat \
            art_price coupon couponcat \
            cart cartitem \
            salecat salephase saleodr saleodrl \
            region regioncat regiontag regionlevel \
            storecat storephase storel offstorel storeodr storeodrl \
            trancat tranphase tranodr tranodrl

