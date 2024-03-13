include ../include/dao.mf

DBNAME = fab

typesdir = ../fab-types
daorst = fab.rst

dao: predao
	$(DAOGEN) \
            artmodel \
            fabfn fabodrl fabodr \
            fabproc_sn fabproc fabstdproc_in fabstdproc \
            fabstdtester \
            fabstdtestcat fabstdtest fabstdtest_parm \
            fabtaskl fabtask \
            fabtrack_op fabtrack fabtrack_test_parm fabtrack_test

