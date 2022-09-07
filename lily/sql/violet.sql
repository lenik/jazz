--\import lily

set search_path = ${1=violet}, lily, public;
create schema if not exists "${1=violet}";

-------- Violet :: Plan --------
--\import violet.plan

-------- Violet :: Artifact --------
--\import violet.art

-------- Violet :: Issue --------
--\import violet.issue

-------- Violet :: Fabrication --------
--\import violet.fab

-------- Violet :: Store --------
--\import violet.store

-------- Violet :: Asset --------
--\import violet.asset

-------- Violet :: Shop & Sales --------
--\import violet.shop

-------- Violet :: Transport --------
--\import violet.tran

-------- Violet :: Edu --------
--\import violet.edu

set search_path = public;
