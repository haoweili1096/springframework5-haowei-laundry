create table clothes (id bigint not null auto_increment, color varchar(255), made_in varchar(255), type_id bigint, owner_id bigint, primary key (id)) engine=InnoDB;
create table owners (id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), address varchar(255), city varchar(255), postal_code varchar(255), telephone varchar(255), primary key (id)) engine=InnoDB;
create table registrars (id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), employee_number varchar(255), primary key (id)) engine=InnoDB;
create table types (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table visits (id bigint not null auto_increment, date date, description varchar(255), cloth_id bigint, primary key (id)) engine=InnoDB;
alter table clothes add constraint FKfyljtifjhqoykmaose4v28vj5 foreign key (type_id) references types (id);
alter table clothes add constraint FK7eqcr66ajlr7sbj8jr5tyt2ur foreign key (owner_id) references owners (id);
alter table visits add constraint FK2sbl5rbmlfwayc8flk4eu8lvd foreign key (cloth_id) references clothes (id);
