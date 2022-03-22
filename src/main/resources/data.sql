insert into USERS (ID, NAME, EMAIL, address_geo_lat, address_geo_lng)
    values
        (1, 'Leanne Graham', 'Sincere@april.biz', -37.3158, 81.1496),
        (2, 'Bobby Graham', 'Sincere@april.com', 37.3159, 8.1496),
        (3, 'Bobby Graham', 'no_album_guy@april.com', 7.3159, 28.1496);
insert into ALBUMS (ID, USER_ID, TITLE)
    values
        (1, 1, '3 sunt qui excepturi placeat culpa'),
        (2, 1, '6 sunt qui excepturi placeat culpa'),
        (3, 2, '1 sunt qui excepturi placeat culpa'),
        (4, 2, '4 sunt qui excepturi placeat culpa'),
        (5, 2, '2 sunt qui excepturi placeat culpa');
insert into IMAGES (ID, ALBUM_ID, TITLE, URL)
    values
        (4849, 1, 'h recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4850, 1, 'j recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4851, 2, 'f recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4852, 2, 'g recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4853, 2, 'a recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4854, 3, 'b recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4855, 4, 'd recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4856, 4, 'c recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4857, 4, 'i recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31'),
        (4858, 4, 'e recusandae provident modi vitae ipsa rerum', 'https://via.placeholder.com/600/4e5c31');