all:

dom0:
	for f in *.htm; do \
		./tran-dom.pl $$f; \
	done

dom1:
	for f in *.htm; do \
		./tran-dom.pl -w $$f; \
	done

io0:
	for f in *.htm; do \
		./tran-io.pl $$f; \
	done

io1:
	for f in *.htm; do \
		./tran-io.pl -w $$f; \
	done

