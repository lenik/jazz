Number system: 

¬ use strict; 
¬ my @num = ('zero', "one", "two", "three", 
¬       qw(four five six seven eight nine), 'ten'); 
¬ for (my $i = 0; $i < 10; $i++) {
    Number { $i } is { $num[$i] }. 
¬ }
