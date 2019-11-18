drop table if exists categories;
create table categories
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

drop table if exists products;
create table products
(
    id          int          not null auto_increment primary key,
    name        varchar(255) not null,
    description varchar(10000),
    category_id int
);
alter table products
    add foreign key (category_id) references categories (id);

drop table if exists product_prices;
create table product_prices
(
    id         int not null auto_increment primary key,
    product_id int not null,
    quantity   int not null,
    price      int not null
);
alter table product_prices
    add foreign key (product_id) references products (id);

drop table if exists product_images;
create table product_images
(
    id         int           not null auto_increment primary key,
    product_id int           not null,
    url        varchar(1024) not null
);
alter table product_images
    add foreign key (product_id) references products (id);