-- ============================================
-- Hostel Management System - MySQL Schema
-- ============================================

CREATE DATABASE IF NOT EXISTS hostel_db;
USE hostel_db;

-- Users
CREATE TABLE IF NOT EXISTS users (
    user_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    role        ENUM('ADMIN','WARDEN','STUDENT') NOT NULL DEFAULT 'STUDENT',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Wardens
CREATE TABLE IF NOT EXISTS wardens (
    warden_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_warden_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Rooms
CREATE TABLE IF NOT EXISTS rooms (
    room_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL UNIQUE,
    capacity    INT NOT NULL DEFAULT 2,
    warden_id   BIGINT,
    CONSTRAINT fk_room_warden FOREIGN KEY (warden_id) REFERENCES wardens(warden_id) ON DELETE SET NULL
);

-- Students
CREATE TABLE IF NOT EXISTS students (
    student_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL UNIQUE,
    name        VARCHAR(100) NOT NULL,
    phone       VARCHAR(15),
    course      VARCHAR(100),
    year        INT,
    room_id     BIGINT,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_student_room FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE SET NULL
);

-- Complaints
CREATE TABLE IF NOT EXISTS complaints (
    complaint_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id      BIGINT NOT NULL,
    title           VARCHAR(200) NOT NULL,
    description     TEXT,
    status          ENUM('PENDING','IN_PROGRESS','RESOLVED') NOT NULL DEFAULT 'PENDING',
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_complaint_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Food Menu
CREATE TABLE IF NOT EXISTS food_menu (
    menu_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    day_of_week ENUM('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY') NOT NULL UNIQUE,
    breakfast   VARCHAR(500),
    lunch       VARCHAR(500),
    dinner      VARCHAR(500)
);

-- Notifications
CREATE TABLE IF NOT EXISTS notifications (
    notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(200) NOT NULL,
    message         TEXT NOT NULL,
    sender_role     ENUM('ADMIN','WARDEN','STUDENT') NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Payments
CREATE TABLE IF NOT EXISTS payments (
    payment_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id  BIGINT NOT NULL,
    amount      DECIMAL(10,2) NOT NULL,
    month       VARCHAR(20) NOT NULL,
    status      ENUM('PAID','NOT_PAID') NOT NULL DEFAULT 'NOT_PAID',
    CONSTRAINT fk_payment_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Attendance
CREATE TABLE IF NOT EXISTS attendance (
    attendance_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id      BIGINT NOT NULL,
    date            DATE NOT NULL,
    status          ENUM('HOSTEL','HOME') NOT NULL DEFAULT 'HOSTEL',
    CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    CONSTRAINT uq_student_date UNIQUE (student_id, date)
);

-- ============================================
-- Seed Data
-- ============================================
-- Default admin (password: admin123 BCrypt-hashed)
INSERT INTO users (username, password, email, role)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@hostel.com', 'ADMIN')
ON DUPLICATE KEY UPDATE username = username;

-- Food menu template
INSERT INTO food_menu (day_of_week, breakfast, lunch, dinner) VALUES
('MONDAY',    'Idli & Sambar',     'Rice, Dal, Sabzi',   'Chapati, Paneer'),
('TUESDAY',   'Poha & Chai',       'Rice, Rajma',        'Chapati, Dal Makhani'),
('WEDNESDAY', 'Upma & Juice',      'Rice, Chicken Curry','Chapati, Mixed Veg'),
('THURSDAY',  'Paratha & Curd',    'Rice, Dal Fry',      'Chapati, Egg Curry'),
('FRIDAY',    'Bread & Omelette',  'Rice, Sambhar',      'Biryani'),
('SATURDAY',  'Puri Bhaji',        'Rice, Chole',        'Chapati, Palak Dal'),
('SUNDAY',    'Dosa & Chutney',    'Pulao & Raita',      'Special Thali')
ON DUPLICATE KEY UPDATE day_of_week = day_of_week;
