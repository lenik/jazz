create table person(
    name        varchar(30) not null,
    type        char(4),
    age         int not null,
    girl        boolean not null,
    location    json,
    height      float not null,
    width       float not null,
    salary      decimal(9, 2),
    tags        varchar(100)[]
);

