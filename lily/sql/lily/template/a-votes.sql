--\import lily.account.user

    create sequence $1_vote_seq start with 1000;

    create table $1_vote(
        id          bigint primary key default nextval('$1_vote_seq'),

        "$1"        ${2=int} not null
            references "$1"(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade,

        --  1 for vote
        -- -1 for hate
        votes       int not null default 1
    );

    create or replace view v_$1_vote as
        select
            o.*,
            u.id        user_id,
            u.label     user_label,
            a.votes
        from $1_vote a
            left join "$1" o    on a."$1" = o.id
            left join "user" u  on a."user" = u.id
            ;
