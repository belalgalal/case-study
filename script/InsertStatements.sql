------------------------------------USER Table
INSERT INTO `case_study`.`user` (`USER_NAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `ENABLED`) VALUES ('belal', '$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', 'belal', 'galal', 1);

INSERT INTO `case_study`.`user` (`USER_NAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `ENABLED`) VALUES ('admin', '$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', 'admin', 'admin', 1);

INSERT INTO `case_study`.`user` (`USER_NAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `ENABLED`) VALUES ('user', '$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', 'user', 'user', 1);

------------------------------------USER_ROLE Table
INSERT INTO `case_study`.`user_role` (`ROLE`, `USER_ID`) VALUES ('ROLE_ADMIN', '1');

INSERT INTO `case_study`.`user_role` (`ROLE`, `USER_ID`) VALUES ('ROLE_USER', '1');

INSERT INTO `case_study`.`user_role` (`ROLE`, `USER_ID`) VALUES ('ROLE_ADMIN', '2');

INSERT INTO `case_study`.`user_role` (`ROLE`, `USER_ID`) VALUES ('ROLE_USER', '3');
------------------------------------COLLECTION Table
INSERT INTO `case_study`.`collection` (`COLLECTION_NAME`, `USER_ID`) VALUES ('Berlin', '1');
------------------------------------CATEGORY Table
INSERT INTO `case_study`.`category` (`CATEGORY_NAME`) VALUES ('Town hall');

INSERT INTO `case_study`.`category` (`CATEGORY_NAME`) VALUES ('Tower');
