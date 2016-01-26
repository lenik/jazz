# Modular SQL Pattern

## Type Extension

    --\import catme.module

    alter table BaseTable add column ExtCol1 int;
    insert into BaseTable(..., ExtCol1) ...

## Standard JSON fields

  - plugins
  - user-data (general purpose)
  - extension (for subclassing)
