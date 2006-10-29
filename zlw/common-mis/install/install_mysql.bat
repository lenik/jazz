@echo off

if "%1"=="" goto help

for %%i in (cdw cuk cux cam cav) do (
	mysql -uroot -pl. -D%1 -f < %%i.sql
)

goto end

:help
echo install_mysql database-name

:end
