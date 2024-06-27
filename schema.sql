create table if not exists realties (id bigserial primary key, 
					   region varchar(100) not null, 
					   city varchar(100) not null,
					  house varchar(100) not null,
					  realty_type varchar(100) not null);

create table if not exists announcements(id bigserial primary key,
										price numeric(10,2) check(price >= 0.01),
										is_active bool not null,
										realty_id bigint references realties(id));
										

create table if not exists booking(id bigserial primary key,
								  date_start date not null,
								  count_day_booking int check(count_day_booking > 0),
								  client varchar(100) unique not null,
								  email varchar(100),
								  id_announcement bigint references announcements(id),
								  price_booking numeric(10,2) check(price_booking >= 0.01));
