all: tab

icons.yml:
	wget ...

tab: conv.pl icons.yml
	./conv.pl $^ >$@

clean:
	rm tab

