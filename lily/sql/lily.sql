set search_path = public;

-------- libsql-great-pg --------
--\import great.pg

set search_path = ${1=lily}, public;
create schema if not exists "${1=lily}";

-------- Lily :: Base --------
--\import lily.base

-------- Lily :: Account --------
--\import lily.account

-------- Lily :: Contact --------
--\import lily.contact

-------- Lily :: Utilities --------
--\import lily.util

-------- Lily :: IO --------
--\import lily.io

-------- Lily :: Internet --------
--\import lily.inet

-------- Lily :: Geography --
--\import lily.geo

-------- Lily :: Reward --------
--\import lily.reward

-------- Lily :: Public --
--\import lily.pub

-------- Lily :: Virtual App --------
--\import lily.vapp

set search_path = public;
