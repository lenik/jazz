define(`REVERSE', 
    `ifelse($#, 0, , $#, 1, $1, N)')dnl
dumpdef(`REVERSE')

define(`ECHO', `$@')dnl

ECHO(hello, world!, 4)
