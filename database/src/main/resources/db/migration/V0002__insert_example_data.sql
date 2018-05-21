INSERT INTO `${prefix}regions` (
    `regionID`, `title`, `map_info_path`, `max_players`
) VALUES (
    '1', 'Dev Room', 'dev_room.tmx', '200'
);

INSERT INTO `${prefix}races` (`id`, `title`, `start_regionID`, `selectable`, `start_pos_x`, `start_pos_y`) VALUES
(1, 'Menschen', 1, 1, 10.0000, 10.0000);