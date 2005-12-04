
M4FLAGS=-P -I..\common-m4
M4=m4 $(M4FLAGS)

all: mail.php string.php

mail.php: mail.m4
	$(M4) mail.m4 >mail.php

string.php : string.m4
	$(M4) string.m4 >string.php
