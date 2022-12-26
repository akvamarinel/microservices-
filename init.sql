create table user_data (
    id uuid not null default gen_random_uuid(),
    name varchar(255) not null,
    surname varchar(255) not null,
    login varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(255) not null,
    primary key(id)
);

create table customer (
    id uuid not null default gen_random_uuid(),
    address text not null,
    user_data_id uuid not null unique references user_data(id) on delete cascade ,
    primary key(id)
);

create table delivery (
    id uuid not null default gen_random_uuid(),
    user_data_id uuid not null unique references user_data(id) on delete cascade ,
    primary key(id)
);

create table user_order(
    id uuid not null default gen_random_uuid(),
    customer_id uuid references customer(id) on delete set null ,
    delivery_id uuid references delivery(id) on delete set null ,
    order_time timestamp not null,
    primary key(id)
);

create table category (
    id uuid not null default gen_random_uuid(),
    name varchar(255) not null unique,
    primary key(id)
);

create table recipe (
    id uuid not null default gen_random_uuid(),
    descr text not null,
    primary key(id)
);

create table foodstuff (
    id uuid not null default gen_random_uuid(),
    name varchar(255) not null unique,
    calories int not null,
    primary key(id)
);

create table food_in_recipe (
    recipe_id uuid not null references recipe(id) on delete cascade,
    foodstuff_id uuid not null references foodstuff(id) on delete cascade,
    weight int not null,
    primary key(recipe_id, foodstuff_id)
);

create table restaurant (
    id uuid not null default gen_random_uuid(),
    name varchar(255) not null,
    rating int not null, -- check (rating > 0 & rating < 6)
    primary key(id)
);

create table dish (
    id uuid not null default gen_random_uuid(),
    name varchar(255) not null,
    restaurant_id uuid references restaurant(id) on delete cascade ,
    recipe_id uuid unique references recipe(id) on delete cascade ,
    primary key(id)
);

create table category_of_dish (
    dish_id uuid not null references dish(id) on delete cascade ,
    category_id uuid not null references category(id) on delete cascade ,
    primary key(dish_id, category_id)
);
