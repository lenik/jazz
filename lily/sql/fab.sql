--\import lily

create schema if not exists "${1=violet}";
set search_path = ${1=violet}, lily, public;

-------- Violet --------
--\import violet

-------- Violet :: Edu --------
--\import violet.fab

set search_path = public;
