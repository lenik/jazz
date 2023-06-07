--\import lily.account.user

    create table user_run(
        "user"      int primary key
            references "user" on update cascade on delete cascade,

        --\mixin lily.mixin.Label
        -- description: the current state text
        
        --\mixin lily.mixin.Ex
        -- state: online, offline, etc.

        --\mixin lily.mixin.Ver
        -- lastmod: active timestamp

        --\mixin lily.mixin.Props
        
        -- @Redundant (maybe)
        score       int not null default 0,     -- Level is score-based.

        lastlog     timestamp with time zone,
        lastlogip   inet
    );

    create index user_run_lastmod  on user_run(lastmod desc);
    create index user_run_score    on user_run(score);

    comment on table user_run is 'User Activity Log';
    comment on column user_run."user" is 'The user';
    comment on column user_run.lastlog is 'Last time of login';
    comment on column user_run.lastlogip is 'The source IP of last login';
