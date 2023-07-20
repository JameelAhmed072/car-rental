create table customer(
	id int auto_increment primary key,
    c_name varchar(100),
    c_number varchar(15),
    c_cnic varchar(50),
    c_address varchar(300),
    ref_phone_number varchar(15)
);

create table vehicle_owner(
	id int auto_increment primary key,
    o_name varchar(100),
    o_cnic varchar(50),
    o_phone_number varchar(15),
    o_address varchar(300),
    commission float
);

create table vehicle(
	id int auto_increment primary key,
    v_name varchar(250),
	v_model int,
    v_brand varchar(250),
    v_color varchar(50),
    owner_id int,
    foreign key (owner_id) references vehicle_owner(id)
);

create table booking(
	id int auto_increment primary key,
    c_id int,
    v_id int,
    booking_date date,
    price double,
    booking_status varchar(50),
    foreign key (c_id) references customer(id),
    foreign key (v_id) references vehicle(id)
);




insert into customer(c_name,c_number,c_cnic,c_address,ref_phone_number) values
	('jameel','321654','132465798','testing','123456789'),
    ('Abdullah','387964','11325798','testing1','123456459'),
    ('Ahmed','321234','13123798','testing2','1245564569'),
	('Raheel','111654','100000798','testing3','1444456789');

insert into vehicle_owner(o_name,o_phone_number,o_cnic,o_address,commission) values
	('raheel','32121654','132465798','testing',12),
    ('Huzaifa','111387964','1131225798','testing12',11),
    ('waji','222321234','13456798','testing23',10);


insert into vehicle(v_name,v_model,v_brand,v_color,owner_id) values
		('Honda insight',2022,'honda','silver',1),
        ('toyota insight',2025,'toyota','green',2),
        ('lexus insight',2021,'lexus','red',3);

insert into booking (c_id,v_id,booking_date,price,booking_status) values
			(1,1,'2023-02-01',2500,'complete'),
            (1,2,'2023-02-08',1500,'not complete'),
            (2,1,'2023-02-09',4500,'complete');




            SELECT * FROM car_rental.booking;

            select * from customer;

            select * from vehicle_owner;


            select * from booking;

            select * from customer as c
            inner join booking as b on c.id = b.c_id;

            select * from customer as c
            left join booking as b on c.id = b.c_id;


            select * from customer as c
            right join booking as b on c.id = b.c_id;

            select c.c_name,b.booking_date,b.price,b.v_id,v.v_name from customer as c
            inner join booking as b on c.id=b.c_id
            inner join vehicle as v on v.id = b.v_id;




            --   find the customer with no bookings

            select * from customer as c
            left join booking as b on c.id = b.c_id where b.c_id is null;



            --      order by

            select c_name,c_cnic from customer order by c_name;


            --   distinct clause (by this we can remove the duplicates and get the unqiue values)


            --  operators and , or

            --   in                  country IN ('USA' , 'France');


            --  between         buyPrice BETWEEN 90 AND 100;


            --     like       firstName LIKE 'a%';  firstname must start with a,  lastName LIKE '%on'; here lastname must end with on.alter


            --      limit    this is used to constrain the number of rows to return. limit clause accept 1 or 2 arguments
            			--   limit 5

            --     isnull     check wheather a value is null or not.   e.g(WHERE salesrepemployeenumber IS NULL)


            --    Group by
            select booking_status, count(*) from booking group by booking_status;
            -- this is almost same as distinct, this generate unique values, but in this we can count the values which is not possible in distinct


            describe customer;
            describe vehicle_owner;

            --  update a row

            select * from customer;

            update customer set c_name = 'Saad' where id = 5;



            -- delete a row

            delete from customer where id = 6;






            --    aggregate functions   avg, count, sum, max, min

            select count(*) as total_customer from customer;

            select sum(price) as total_price from booking;

            select * from booking;

            select max(price) as max_price from booking;

            select min(price) as max_price from booking;

            select avg(price) as max_price from booking;






--   Class Task

 select * from booking;

  select * from customer;

   select * from vehicle;

    select * from vehicle_owner;


select o_name,v_name,v_model, v_brand from vehicle_owner as vo
inner join vehicle as v on vo.id = v.owner_id where o_name = 'raheel';


--   task 2 ,    count the number of vechicle of specific owner

select o_name, count(*) as Number_of_vehicles from vehicle_owner as vo
inner join vehicle as v on vo.id = v.owner_id where o_name = 'raheel';



--   task 3 ,    tell the booking of vechilces of specified owner , and show its date, o_name, price


select o_name,v_name,v_model,booking_date,price from vehicle_owner as vo
inner join vehicle as v on vo.id = v.owner_id
inner join booking as b on b.v_id = v.id where booking_status='complete';







create table user(
	id int primary key auto_increment,
    username varchar(255),
    pass varchar(255)
);

insert into user(username, pass) values ('Toqeer','Ahmed');
insert into user(username, pass) values ('Waqar','Wiki');


select * from user;