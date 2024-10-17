INSERT INTO concert (concert_name, concert_location, concert_fee, concert_item_id)
VALUE ("루시의 열 콘서트", "서울 상암동", 100000, 1);

INSERT INTO seat (concert_item_id, all_seat, remainder_seat)
VALUES (1, 50, 50);


INSERT INTO concert_item (reservation_start, reservation_end, concert_start)
VALUES ('2024-12-15 19:00:00', '2024-12-15 22:00:00', '2024-10-01 09:00:00');


