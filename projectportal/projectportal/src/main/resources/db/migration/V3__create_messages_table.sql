CREATE TABLE messages(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content text NOT NULL,
    sent_at DATETIME NOT NULL,
    CONSTRAINT fk_message_application FOREIGN KEY (application_id) REFERENCES application(id) ON DELETE CASCADE,
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_message_receiver FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
)
