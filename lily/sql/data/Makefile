SHELL = /bin/bash

china-2013.sql: china-2013.csv
	echo -n "insert into zone(id, cat, code, label) values" >$@
	id=-100; \
	while IFS=: read code label; do \
	    test "$$id" = -100 || echo -n ","; \
	    echo; \
	    echo -n "    ($$id, 2, '$$code', '$$label')"; \
	    (( id-- )); \
	done <$< >>$@
	echo ";" >>$@

