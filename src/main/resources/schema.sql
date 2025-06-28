CREATE TABLE IF NOT EXISTS branches (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    layout_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pcs (
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


CREATE TABLE IF NOT EXISTS promotions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    type VARCHAR(50),
    platform VARCHAR(50),
    description TEXT,
    image_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE
);