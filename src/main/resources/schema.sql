CREATE TABLE branches (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    layout_url VARCHAR(255)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(50),
    username VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    is_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE pcs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    processor VARCHAR(255),
    gpu VARCHAR(255),
    motherboard VARCHAR(255),
    ram VARCHAR(255),
    disk VARCHAR(255),
    games_installed VARCHAR(255),
    monitor_hz INT,
    status VARCHAR(50),
    branch_id BIGINT,
    FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    pc_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (pc_id) REFERENCES pcs(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE promotions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    type VARCHAR(50),
    platform VARCHAR(50),
    description TEXT,
    image_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE
);