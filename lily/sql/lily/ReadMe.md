# Modular SQL Pattern

## Type Extension

    --\import catme.module

    alter table BaseTable add column ExtCol1 int;
    insert into BaseTable(..., ExtCol1) ...

## Standard JSON fields

  - plugins
  - user-data (general purpose)
  - extension (for subclassing)

## telok/emailok validation

  - 用一种时间片滚动的方法，比如每个5分钟用固定算法得出一个校验码
    当用户输入校验码时，只要在附近几个时间片内都算有效，这样就不用保存检验码了。

