use JobApplication;

# to get the total rowcount
SELECT sum(TABLE_ROWS)
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = "JobApplication";

# to get the rowcount for each table
SELECT TABLE_NAME, TABLE_ROWS
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = "JobApplication";