include coolmake/m2proj.mf

DBNAME=fab

remotehost = localhost
remotedir = lily-erp

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

typesdir=../fab-types

DAOGEN = daogen $(DAOFLAGS) \
    -H $(typesdir) \
    -c ../sql/out/fab.rst \
    -p net.bodz.other.schema

predao:
	cd ../sql; make -B

dao: predao
	$(DAOGEN) \
            artmodel \
            fabfn fabodrl fabodr \
            fabproc_sn fabproc fabstdproc_in fabstdproc \
            fabstdtester \
            fabstdtestcat fabstdtest fabstdtest_parm \
            fabtaskl fabtask \
            fabtrack_op fabtrack fabtrack_test_parm fabtrack_test

del-exporters-samples: del-samples del-exporters

del-samples: predao
	find -name *Samples.java -print -delete
	find -name *MapperTest.java -print -delete

del-exporters: predao
	find -name *Exporter.java -print -delete

