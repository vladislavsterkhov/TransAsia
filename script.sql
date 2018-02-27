create table part (
    partName varchar(255),
    partNumber varchar(255),
    vendor varchar(255),
    qty int,
    shipped Date,
    receive Date
);
	 

 insert into part(partName,
			partNumber,
			vendor,
			qty,
			shipped,
			receive) values (
				'test1',
				'test1',
				'test1',
				1,
				'01.01.2018',
				'01.01.2018'
			);
			
 insert into part(partName,
			partNumber,
			vendor,
			qty,
			shipped,
			receive) values (
				'test2',
				'test2',
				'test2',
				2,
				'02.01.2018',
				null
			);
			
insert into part(partName,
			partNumber,
			vendor,
			qty,
			shipped,
			receive) values (
				'test3',
				'test3',
				'test3',
				3,
				null,
				'03.01.2018'
			);
			
			
			 insert into part(partName,
			partNumber,
			vendor,
			qty,
			shipped,
			receive) values (
				'test4',
				'test4',
				'test4',
				4,
				null,
				null
			);
			
			
			 insert into part(partName,
			partNumber,
			vendor,
			qty,
			shipped,
			receive) values (
				'test5',
				'test5',
				'test5',
				5,
				'05.01.2018',
				'05.01.2018'
			);