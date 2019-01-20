        -- the region body is made up as the specific artifact.
        alter table region add column material  int
            references art(id) on update cascade;
            
        -- reserved for this artifact category.
        alter table region add column for_cat   int
            references artcat(id) on update cascade on delete set null;

        -- reserved for this artifact.
        alter table region add column for_art   int     -- state=LOCKED if necessary.
            references art(id) on update cascade on delete set null;
            
        -- (cache) filled with this artifact.
        alter table region add column art       int     -- state=LOCKED if necessary.
            references art(id) on update cascade on delete set null;

