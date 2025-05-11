-- Inserting dummy data into the `admin` table
INSERT INTO admin (id, name, email, googleId, profilePicUrl) VALUES
                                                                 (UUID(), 'Alice Smith', 'alice.smith@example.com', 'google_id_1', 'https://example.com/alice.jpg'),
                                                                 (UUID(), 'Bob Johnson', 'bob.johnson@example.com', 'google_id_2', 'https://example.com/bob.jpg');

-- Inserting dummy data into the `segment` table
INSERT INTO segment (id, name, description, ruleDefinition, createdAt, updatedAt, created_by) VALUES
    (UUID(), 'Active Users', 'Users who have made a purchase in the last 30 days', '{"age": "25-35", "lastPurchase": "30"}', NOW(), NOW(), (SELECT id FROM admin WHERE email = 'alice.smith@example.com' LIMIT 1)),
(UUID(), 'Frequent Buyers', 'Users who have purchased more than 5 items', '{"totalOrders": "5+"}', NOW(), NOW(), (SELECT id FROM admin WHERE email = 'bob.johnson@example.com' LIMIT 1));

-- Inserting dummy data into the `customer` table
INSERT INTO customer (id, name, email, phone, gender, address, password, registrationDate, lastPurchaseDate, totalOrders, totalSpend, isActive) VALUES
                                                                                                                                                    (UUID(), 'John Doe', 'john.doe@example.com', '1234567890', 'Male', '1234 Elm St', 'password123', NOW(), NOW(), 5, 100.00, true),
                                                                                                                                                    (UUID(), 'Jane Doe', 'jane.doe@example.com', '0987654321', 'Female', '5678 Oak St', 'password456', NOW(), NOW(), 2, 50.00, true),
                                                                                                                                                    (UUID(), 'Michael Brown', 'michael.brown@example.com', '2345678901', 'Male', '9101 Pine St', 'password789', NOW(), NOW(), 1, 30.00, true),
                                                                                                                                                    (UUID(), 'Emily White', 'emily.white@example.com', '3456789012', 'Female', '1234 Maple St', 'password321', NOW(), NOW(), 3, 75.00, true);

-- Inserting dummy data into the `campaign` table
INSERT INTO campaign (id, name, timestamp, audienceSize, segmentRule, created_by, segment_id) VALUES
                                                                                                  (UUID(), 'Summer Sale', NOW(), 5000, '{"age": "18-40", "spend": "50+"}', (SELECT id FROM admin WHERE email = 'alice.smith@example.com' LIMIT 1), (SELECT id FROM segment WHERE name = 'Active Users' LIMIT 1)),
(UUID(), 'Winter Clearance', NOW(), 3000, '{"season": "winter"}', (SELECT id FROM admin WHERE email = 'bob.johnson@example.com' LIMIT 1), (SELECT id FROM segment WHERE name = 'Frequent Buyers' LIMIT 1)),
(UUID(), 'Black Friday Deals', NOW(), 10000, '{"holiday": "Black Friday"}', (SELECT id FROM admin WHERE email = 'alice.smith@example.com' LIMIT 1), (SELECT id FROM segment WHERE name = 'Active Users' LIMIT 1));

-- Inserting dummy data into the `communication_log` table
INSERT INTO communication_log (id, message, status, sentAt, campaign_id, customer_id) VALUES
                                                                                          (UUID(), 'Summer Sale: 50% off!', 'SENT', NOW(), (SELECT id FROM campaign WHERE name = 'Summer Sale' LIMIT 1), (SELECT id FROM customer WHERE email = 'john.doe@example.com' LIMIT 1)),
(UUID(), 'Winter Clearance: Final Sale!', 'SENT', NOW(), (SELECT id FROM campaign WHERE name = 'Winter Clearance' LIMIT 1), (SELECT id FROM customer WHERE email = 'jane.doe@example.com' LIMIT 1)),
(UUID(), 'Black Friday Deals: Don\'t miss out!', 'SENT', NOW(), (SELECT id FROM campaign WHERE name = 'Black Friday Deals' LIMIT 1), (SELECT id FROM customer WHERE email = 'michael.brown@example.com' LIMIT 1)),
(UUID(), 'Summer Sale: Limited time offer!', 'FAILED', NOW(), (SELECT id FROM campaign WHERE name = 'Summer Sale' LIMIT 1), (SELECT id FROM customer WHERE email = 'emily.white@example.com' LIMIT 1)),
(UUID(), 'Winter Clearance: Get up to 70% off!', 'SENT', NOW(), (SELECT id FROM campaign WHERE name = 'Winter Clearance' LIMIT 1), (SELECT id FROM customer WHERE email = 'john.doe@example.com' LIMIT 1));

-- Inserting dummy data into the `order` table
INSERT INTO customerOrder (id, orderId, orderDate, amount, customer_id) VALUES
(UUID(), 'ORD12345', NOW(), 50.00, (SELECT id FROM customer WHERE email = 'john.doe@example.com' LIMIT 1)),
(UUID(), 'ORD12346', NOW(), 30.00, (SELECT id FROM customer WHERE email = 'jane.doe@example.com' LIMIT 1)),
(UUID(), 'ORD12347', NOW(), 20.00, (SELECT id FROM customer WHERE email = 'michael.brown@example.com' LIMIT 1)),
(UUID(), 'ORD12348', NOW(), 75.00, (SELECT id FROM customer WHERE email = 'emily.white@example.com' LIMIT 1)),
(UUID(), 'ORD12349', NOW(), 100.00, (SELECT id FROM customer WHERE email = 'john.doe@example.com' LIMIT 1));
