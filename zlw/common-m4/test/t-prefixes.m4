m4_include(`pdo-member.m4')

Test00 = "pdo_addprefixes(`my_')"
Test01 = "pdo_addprefixes(`my_', arg1)"
Test02 = "pdo_addprefixes(`my_', arg1, arg2)"
Test03 = "pdo_addprefixes(`my_', arg1, arg2, arg3)"

pdo_member(`mem1'); 
pdo_member(`mem1', CDATA); 
pdo_member(`mem3', RAW, NOTNULL); 
pdo_member(`mem4', VERIFY, GET, PUT); 

