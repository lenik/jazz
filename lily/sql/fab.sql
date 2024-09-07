--\import lily

-------- Violet --------
--\import violet

-- shared schema with violet
-- create schema if not exists "${1=fab}";
set search_path = ${1=violet}, lily, public;

-------- Violet :: Fab --------
--\import violet.fab

set search_path = public;

