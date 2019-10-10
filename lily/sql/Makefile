all.sql:
	#catme violet.sql >$@
	catme violet.sql \
	    | sed -e "s/,\s*--\s*\(.*\)\$$/ comment '\1',/" \
	    | tee $@ | xsel -b

