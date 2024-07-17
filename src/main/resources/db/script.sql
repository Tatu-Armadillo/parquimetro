-- do $$
-- begin
--   if not exists (
--         select 1
--         from information_schema.schemata
--         where schema_name = 'challenge'
--     ) then
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

        create table address(
            id_address bigserial primary key,
            cep varchar(8) not null,
            public_place varchar(255),
            complement varchar(255),
            neighborhood varchar(255),
            city varchar(255) not null,
            uf_state varchar(2) not null,
            ibge varchar(7) not null
        );

        create table conductor(
            id_conductor bigserial primary key,
            name varchar(255) not null,
            cpf varchar(11) unique not null,
            phone varchar(13) unique not null,
            email varchar(255) unique not null,
            payment_format varchar(11) not null,
            address bigint not null,
            users bigint
        );

        create table vehicle(
            id_vehicle bigserial primary key,
            license_plate varchar(7) unique not null,
            chassis varchar(17) unique not null,
            manufacturing_year integer not null,
            model_year integer not null,
            brand varchar(255) not null,
            model varchar(255) not null,
            color varchar(100) not null,
            fuel varchar(35) not null,
            buy_date date not null,
            conductor bigint
        );

        create table establishment(
            id_establishment bigserial primary key,
            name varchar(255) not null,
            cnpj varchar(14) unique not null,
            price_hour numeric(10,2) not null,
            address bigint not null,
            users bigint
        );

        create table parking(
            id_parking bigserial primary key,
            time_fixed boolean not null,
            time_start timestamp,
            time_end timestamp,
            total_time integer,
            total_price numeric(10,2),
            vehicle bigint,
            establishment bigint
        );

        create table payment(
            id_payment bigserial primary key,
            create_date timestamp not null,
            payment_date timestamp not null,
            payment_format varchar(11) not null,
            payment_completed boolean not null,
            parking bigint
        );

        alter table vehicle add constraint chk_fuel check (fuel in ('ALCOHOL', 'GASOLINE', 'DIESEL', 'NATURAL_GAS', 'ELECTRIC', 'HYDROGEN', 'FLEX_ALCOHOL_GASOLINE', 'HYBRID_ALCOHOL_GASOLINE_ELECTRIC'));
        alter table conductor add constraint chk_payment_format_payment check (payment_format in ('CREDIT_CARD', 'DEBIT_CARD', 'PIX'));
        alter table payment add constraint chk_payment_format_payment check (payment_format in ('CREDIT_CARD', 'DEBIT_CARD', 'PIX'));

        alter table user_permission add constraint fk_user_permission_user foreign key (id_user) references users (id_user);
        alter table user_permission add constraint fk_user_permission_permission foreign key (id_permission) references permission (id_permission);
        alter table conductor add constraint fk_conductor_address foreign key (address) references address (id_address);
        alter table conductor add constraint fk_conductor_users foreign key (users) references users (id_user);
        alter table vehicle add constraint fk_vehicle_conductor foreign key (conductor) references conductor (id_conductor);
        alter table establishment add constraint fk_establishment_address foreign key (address) references address (id_address);
        alter table establishment add constraint fk_establishment_users foreign key (users) references users (id_user);
        alter table parking add constraint fk_parking_vehicle foreign key (vehicle) references vehicle (id_vehicle);
        alter table parking add constraint fk_parking_establishment foreign key (establishment) references establishment (id_establishment);
        alter table payment add constraint fk_payment_parking foreign key (parking) references parking (id_parking);

        insert into challenge.permission (description) values ('ADMINISTRATOR'), ('CONDUCTOR'), ('OWNER_ESTABLISHMENT');
        -- password 123456
        insert into challenge.users (user_name,full_name,"password",account_non_expired,account_non_locked,credentials_non_expired,enabled) VALUES ('adm','adm','$2a$10$PqsrFKSSRev9lL0BMAE.IOvDB4r6plBA7c45UDzz4v0Wu1Es9XMs.',true,true,true,true);
        insert into challenge.user_permission (id_user,id_permission) VALUES (1,1), (1,2), (1,3);

--     end if;
-- end $$;