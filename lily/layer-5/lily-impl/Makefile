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

DAOGEN = daogen $(DAOFLAGS)

DAOGEN_LILY = $(DAOGEN) \
    -H ../../layer-2/lily-model \
    -c sql/out/lily.rst

DAOGEN_VIOLET = $(DAOGEN) \
    -H ../../layer-2/lily-model \
    -c sql/out/violet.rst

predao:
	cd sql; make -B

dao-base: predao
	$(DAOGEN_LILY) -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock

dao-account: predao
	$(DAOGEN_LILY) -p net.bodz.lily.security \
            usertype grouptype user usercat useroid useroidtype usersec user_run group

dao-contact: predao
	$(DAOGEN_LILY) -p net.bodz.lily.contact \
            org orgunit partycat person personrole

dao-geo: predao
	$(DAOGEN_LILY) -p net.bodz.lily.geo \
            zone zonecat

dao-inet: predao
	$(DAOGEN_LILY) -p net.bodz.lily.inet \
            extsite

dao-io: predao
	$(DAOGEN_LILY) -p net.bodz.lily.io \
            storage

dao-pub: predao
	$(DAOGEN_LILY) -p net.bodz.lily.pub \
            article* post*

dao-reward: predao
	$(DAOGEN_LILY) -p net.bodz.lily.reward \
            badge user_badge

dao-util: predao
	$(DAOGEN_LILY) -p net.bodz.lily.util \
            uom

dao-security: predao
	$(DAOGEN_LILY) -p net.bodz.lily.util \
            user usercat useroid usersec user_run group policy

dao-vapp: predao
	$(DAOGEN_LILY) -p net.bodz.lily.vapp \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-lily-glob: predao
	$(DAOGEN_LILY) -p net.bodz.lily.other \
            -T lily.*

dao-lily: predao
	$(DAOGEN_LILY) -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock \
            usertype grouptype user usercat useroid useroidtype usersec user_run group \
            org orgunit partycat person personrole \
            zone zonecat \
            extsite \
            storage \
            'article*' 'post*' \
            badge user_badge \
            uom \
            user usercat useroid usersec user_run group policy \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-art: predao
	$(DAOGEN_VIOLET) -p net.bodz.violet.art \
            art artcat artparm artphase arttag art_backref art_doc art_vote artpack uom

dao-asset:
	$(DAOGEN_VIOLET) -p net.bodz.violet.asset \
            net.bodz.violet.asset.AbstractAsset \
            asset asset_group asset_org asset_ou asset_person asset_user

dao-edu:
	$(DAOGEN_VIOLET) -p net.bodz.violet.edu \
            course coursecat coursetag course_fav coursekit coursekitcat coursekittag coursekit_fav \
            testq testans testqtag testq_fav testq_vote testq_msg testq_msg_vote \
            testpaper testpaperl testapply testapplyl

dao-issue:
	$(DAOGEN_VIOLET) -p net.bodz.violet.issue \
            issue issuecat issueparm issuephase issuetag issue_fav issue_vote \
            issuelog issuelog_vote

dao-plan:
	$(DAOGEN_VIOLET) -p net.bodz.violet.issue \
            diary diarycat diaryparm diaryphase diarytag diary_party diary_vote \
            diaryrev diaryrev_vote \
            plan plancat planparm planphase plantag plan_fav plan_party plan_vote \
            plando plandoparm plandotag plando_vote

dao-shop:
	$(DAOGEN_VIOLET) -p net.bodz.violet.issue \
            shop shopcat shoptag shop_fav \
            shopitem shopitemcat shopitem_fav \
            shopmem shopmemcat \
            art_price coupon couponcat \
            cart cartitem \
            salecat salephase saleodr saleodrl

dao-store:
	$(DAOGEN_VIOLET) -p net.bodz.violet.issue \
            region regioncat regiontag regionlevel \
            storecat storephase storel offstorel storeodr storeodrl

dao-tran:
	$(DAOGEN_VIOLET) -p net.bodz.violet.issue \
            trancat tranphase tranodr tranodrl

dao-violet-glob: predao
	$(DAOGEN_VIOLET) -p net.bodz.violet.other \
            -T violet.*

dao-violet: predao
	$(DAOGEN_VIOLET) -p net.bodz.violet.other \
            art artcat artparm artphase arttag art_backref art_doc art_vote artpack uom \
            net.bodz.violet.asset.AbstractAsset \
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

