define(`foo', `Defined-FOO')dnl
define(`bar1', BAR1-with-foo-inline)dnl
define(`bar2', `BAR2-with-foo-inline')dnl
define(`bar3', BAR3-with-`foo'-inline)dnl
define(`bar4', ``BAR4-with-foo-inline'')dnl
`bar1' = bar1
`bar2' = bar2
`bar3' = bar3
`bar4' = bar4
