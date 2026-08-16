-- 1. Create Users
INSERT INTO app_user (user_name) VALUES ('Alex');
INSERT INTO app_user (user_name) VALUES ('Karthik');
INSERT INTO app_user (user_name) VALUES ('Priya');
INSERT INTO app_user (user_name) VALUES ('Vikram');

-- 2. Create Ride Groups
-- Group 1: Active ride to Yelagiri
INSERT INTO ride_group (group_name, destination_lat, destination_long, status)
VALUES ('CB350 RS Sunday Ride', 12.5795, 78.6436, 'ACTIVE');

-- Group 2: Completed past ride
INSERT INTO ride_group (group_name, destination_lat, destination_long, status)
VALUES ('Yercaud Dawn Run', 11.7753, 78.2093, 'COMPLETED');

-- 3. Add Users to Group 1
INSERT INTO group_member_ship (user_id, group_id, joined_at) VALUES (1, 1, CURRENT_TIMESTAMP);
INSERT INTO group_member_ship (user_id, group_id, joined_at) VALUES (2, 1, CURRENT_TIMESTAMP);
INSERT INTO group_member_ship (user_id, group_id, joined_at) VALUES (3, 1, CURRENT_TIMESTAMP);

-- 4. Set Initial Live Locations (Scattered around Thiruppathur heading towards Yelagiri)
-- Alex is at the front
INSERT INTO live_location (user_id, group_id, latitude, longitude, last_updated)
VALUES (1, 1, 12.5100, 78.5800, CURRENT_TIMESTAMP);

-- Karthik is in the middle
INSERT INTO live_location (user_id, group_id, latitude, longitude, last_updated)
VALUES (2, 1, 12.4939, 78.5670, CURRENT_TIMESTAMP);

-- Priya is trailing slightly
INSERT INTO live_location (user_id, group_id, latitude, longitude, last_updated)
VALUES (3, 1, 12.4850, 78.5500, CURRENT_TIMESTAMP);