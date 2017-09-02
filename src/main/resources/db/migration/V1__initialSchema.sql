CREATE TABLE service_requests (
  uniqueKey   BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
  createdDate TIMESTAMP,
  closedDate  TIMESTAMP,
  addressType VARCHAR(50) NOT NULL,
  agencyName  VARCHAR(100),
  agency      VARCHAR(50) NOT NULL
);