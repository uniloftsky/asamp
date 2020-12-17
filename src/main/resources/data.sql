INSERT INTO COUNTER_AGENT (email, first_name, last_name, middle_name, phone) VALUES ('mail@mail.com', 'Антон', 'Кулик', 'Олегович', '1234')
INSERT INTO COUNTER_AGENT (email, first_name, last_name, middle_name, phone) VALUES ('mail@mail.com', 'Сергій', 'Чаюк', 'Олександрович', '1234')
INSERT INTO COUNTER_AGENT (email, first_name, last_name, middle_name, phone) VALUES ('mail@mail.com', 'Олександр', 'Дученко', 'Кубоголовий', '1234')

INSERT INTO ITEM_NAME (item_name) VALUES ('Підшипник')

INSERT INTO ITEM_TYPE (type_name) VALUES ('Мініатюрний')
INSERT INTO ITEM_TYPE (type_name) VALUES ('Кульковий радіальний')
INSERT INTO ITEM_TYPE (type_name) VALUES ('Радіально-упорно кульковий')
INSERT INTO ITEM_TYPE (type_name) VALUES ('Радіальний дворядний сферичний самовстановлюваний')
INSERT INTO ITEM_TYPE (type_name) VALUES ('Упорно-кульковий')
INSERT INTO ITEM_TYPE (type_name) VALUES ('Закріплювальний кульковий')

INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (1, 20, 40, 1, 1)
INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (5, 20, 40, 1, 2)
INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (12, 12, 28, 1, 3)
INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (1, 20, 40, 1, 4)
INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (2, 17, 35, 1, 5)
INSERT INTO ITEM (count, in_diameter, out_diameter, item_name_id, item_type_id) VALUES (1, 20, 40, 1, 6)

INSERT INTO ITEM_ADD (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (2, '2020-12-13', 56, 1, 1, 1)
INSERT INTO ITEM_ADD (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (12, '2020-11-14', 210, 2, 1, 2)
INSERT INTO ITEM_ADD (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (50, '2020-11-20', 189, 3, 1, 3)

INSERT INTO ITEM_REMOVE (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (5, '2020-11-07', 228, 1, 1, 2)
INSERT INTO ITEM_REMOVE (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (17, '2020-11-01', 12, 2, 1, 4)
INSERT INTO ITEM_REMOVE (count, date, price, counter_agent_id, item_name_id, item_type_id) VALUES (41, '2020-11-18', 15, 3, 1, 5)