begin;
	
	-- 用户表
	drop table if exists `user_info`;
	create table `user_info` (
		`id` int(11) not null,
		`user_name` VARCHAR(30) not null,
		`login_id` VARCHAR(30) not null,
		`pwd` VARCHAR(30) not null,
		`phone_no` VARCHAR(30),
		`id_card_no` VARCHAR(30),
		`identity` VARCHAR(30),
		`is_active` boolean,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 订单表
	drop table if exists `order_info`;	
	create table `order_info` (
		`id` int(11) not null,
		`user_id` int(11) not null,
		`user_name` VARCHAR(30),
		`create_time` datetime not null,
		`order_status` VARCHAR(10),
		`total_amount` float(10,2),
		`delivery_address` VARCHAR(60),
		`delivery_phone_no` VARCHAR(60),
		`payment_time` datetime,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 订单商品表
	drop table if exists `order_item`;
	create table `order_item` (
		`id` int(11) not null,
		`order_id` int(11) not null,
		`item_id` int(11) not null,
		`item_name` VARCHAR(30),
		`store_id` int(11) not null,
		`store_name` VARCHAR(30),
		`unit_price` float(10,2),
		`amount` int(3),
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 商家表
	drop table if exists `store`;
	create table `store` (
		`id` int(11) not null,
		`store_name` VARCHAR(30) not null,
		`entry_date` date,
		`contacts` VARCHAR(30) not null,
		`telephone` VARCHAR(30),
		`introduction` VARCHAR(60),
		`is_active` boolean,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 商品表
	drop table if exists `store_item`;
	create table `store_item` (
		`id` int(11) not null,
		`item_name` VARCHAR(30),
		`store_id` int(11) not null,
		`store_name` VARCHAR(30),
		`unit_price` float(10,2),
		`inventory` int(3),
		`categorys` VARCHAR(60),
		`delivery_address` VARCHAR(60),
		`description` VARCHAR(30),
		`is_active` boolean,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 商品类别表
	drop table if exists `product_category`;
	create table `product_category` (
		`id` int(11) not null,
		`item_name` VARCHAR(30),
		`item_id` int(11) not null,
		`category_id` int(11) not null,
		`category_name` VARCHAR(30),
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;

	-- 类别		
	drop table if exists `category`;
	create table `category` (
		`id` int(11) not null,
		`name` VARCHAR(10),
		`description` VARCHAR(30),
		`is_active` boolean,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 购物车	
	drop table if exists `shopping_cart`;
	create table `shopping_cart` (
		`id` int(11) not null,
		`user_id` VARCHAR(10),
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;
	
	-- 购物车列表	
	drop table if exists `shopping_cart_list`;
	create table `shopping_cart_list` (
		`id` int(11) not null,
		`item_id` int(11) not null,
		`shopping_cart_id` int(11) not null,
		`adding_time` datetime not null,
		PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB;

end;
commit;

