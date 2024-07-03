DELETE FROM tbl_address WHERE EXISTS ( SELECT 1 FROM tbl_address );

DELETE FROM tbl_person WHERE EXISTS ( SELECT 1 FROM tbl_person );