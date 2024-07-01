do $$ 
begin
  if not exists (
        select 1
        from information_schema.schemata
        where schema_name = 'challenge'
    ) then
        drop schema if exists challenge cascade; -- REMOVE
        create schema challenge;

        create table permission (
            id_permission bigserial primary key,
            description varchar(255) unique
        );

        create table users(
            id_user bigserial primary key,
            user_name varchar(255) unique not null,
            full_name varchar(255) not null,
            password varchar(255) not null,
            account_non_expired boolean not null,
            account_non_locked boolean not null,
            credentials_non_expired boolean not null,
            enabled boolean not null
        );

        create table user_permission(
            id_user bigint,
            id_permission bigint,
            primary key ( id_user, id_permission)
        );


    alter table user_permission add constraint fk_user_permission_user foreign key (id_user) references users (id_user);
    alter table user_permission add constraint fk_user_permission_permission foreign key (id_permission) references permission (id_permission);
    
    insert into challenge.permission (description) values ('ADMINISTRATOR');
    -- password 123456
    INSERT INTO challenge.users (user_name,full_name,"password",account_non_expired,account_non_locked,credentials_non_expired,enabled) VALUES ('adm','adm','$2a$10$PqsrFKSSRev9lL0BMAE.IOvDB4r6plBA7c45UDzz4v0Wu1Es9XMs.',true,true,true,true);
    INSERT INTO challenge.user_permission (id_user,id_permission) VALUES (1,1);

    end if;
end $$;