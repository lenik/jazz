include ../include/dao.mf

DBNAME = lily

typesdir = ../basic-types
daorst = lily.rst

dao-base: predao
	$(DAOGEN) _schema _cat _form _formparm _parm _parmval _phase _priority _tagv _tag _rlock

dao-account: predao
	$(DAOGEN) usertype grouptype user usercat useroid useroidtype usersec user_run group policy

dao-contact: predao
	$(DAOGEN) org orgunit partycat person personrole persontag person_tag

dao-person: predao
	$(DAOGEN) person

dao-geo: predao
	$(DAOGEN) zone zonecat

dao-inet: predao
	$(DAOGEN) extsite

dao-io: predao
	$(DAOGEN) storage

dao-pub: predao
	$(DAOGEN) article* post*

dao-reward: predao
	$(DAOGEN) badge user_badge

dao-util: predao
	$(DAOGEN) uom

dao-vapp: predao
	$(DAOGEN) apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-lily-glob: predao
	$(DAOGEN) -T lily.*

dao: predao
	$(DAOGEN) \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock \
	    usertype grouptype user usercat useroid useroidtype usersec user_run group policy \
	    org orgunit partycat person personrole persontag person_tag \
            zone zonecat \
            extsite \
            storage \
            'article*' 'post*' \
            badge user_badge \
            uom \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

