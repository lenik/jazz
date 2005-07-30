@echo off

if "%M4PAPTH%"=="" set M4PATH=%~dp0..\common-m4:%~dp0..\config

echo configure smtp api ...
smtp.m4 > smtp.php

echo configure string utilities ...
string.m4 > string.php

echo configure default DBI connection ...
dbi.m4 > dbi.php

