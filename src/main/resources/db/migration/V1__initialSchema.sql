CREATE TABLE service_requests (
  uniqueKey   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  addressType VARCHAR(50) NOT NULL,
  agency      VARCHAR(50) NOT NULL
);

