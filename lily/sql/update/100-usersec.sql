
    alter table usersec drop salt;
    alter table usersec drop pubkey;
    alter table usersec drop email;
    alter table usersec drop emailok;
    alter table usersec drop tel;
    alter table usersec drop telok;
    alter table usersec rename ques to question;
    alter table usersec rename ans to answer;
    
    update useroidtype set label='email' where id=1;
    insert into useroidtype(id, label) values(2, 'mobile');

    -- type: 1 mobile, 2 email
    insert into useroid("user", type, oid) values(0, 1, '83592583@qq.com');
    insert into useroid("user", type, oid) values(0, 2, '13819471680');
    
