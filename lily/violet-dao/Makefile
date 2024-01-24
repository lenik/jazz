include coolmake/m2proj.mf

remotehost = localhost
remotedir = lily-impl

bin_files += \
    src/

src_excludes += \
    Makefile \
    backup \
    doc \
    home

run:
	screen -S $(remotedir) main LilyTestd

run-daemon:
	screen -S $(remotedir) -d -m main LilyTestd

debug:
	main -d LilyTestd

apidir=../lily-api
typesdir=../basic-types

DAOGEN = daogen $(DAOFLAGS)

DAOGEN_VIOLET = $(DAOGEN) \
    -H $(typesdir) \
    -c sql/out/violet.rst

predao:
	cd sql; make -B

dao-art: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.art \
            art artcat artparm artphase arttag \
            art_backref art_doc art_vote artpack \
            uom

dao-asset: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.asset \
            net.bodz.violet.asset.AbstractAsset \
            asset asset_group asset_org asset_ou asset_person asset_user

dao-edu: predao
	$(DAOGEN_VIOLET) \
        -p net.bodz.violet.edu \
            course coursecat coursetag course_fav coursekit coursekitcat coursekittag coursekit_fav \
            testq testans testqtag testq_fav testq_vote testq_msg testq_msg_vote \
            testpaper testpaperl testapply testapplyl

dao-issue: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.issue \
            issue issuecat issueparm issuephase issuetag issue_fav issue_vote \
            issuelog issuelog_vote

dao-plan: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.issue \
            diary diarycat diaryparm diaryphase diarytag diary_party diary_vote \
            diaryrev diaryrev_vote \
            plan plancat planparm planphase plantag plan_fav plan_party plan_vote \
            plando plandoparm plandotag plando_vote

dao-shop: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.issue \
            shop shopcat shoptag shop_fav \
            shopitem shopitemcat shopitem_fav \
            shopmem shopmemcat \
            art_price coupon couponcat \
            cart cartitem \
            salecat salephase saleodr saleodrl

dao-store: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.issue \
            region regioncat regiontag regionlevel \
            storecat storephase storel offstorel storeodr storeodrl

dao-tran: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.issue \
            trancat tranphase tranodr tranodrl

dao-violet-glob: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.other \
            -T violet.*

dao-violet: predao
	$(DAOGEN_VIOLET) \
            -p net.bodz.violet.other \
            art artcat artparm artphase arttag art_backref art_doc art_vote artpack uom \
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

del-exporters-samples: del-samples del-exporters

del-samples: predao
	find -name *Samples.java -print -delete
	find -name *MapperTest.java -print -delete

del-exporters: predao
	find -name *Exporter.java -print -delete

