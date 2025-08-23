-- create database pc_store;
-- use pc_store;

-- create table NSX (
-- 	MaNSX varchar(255) primary key,
--     TenNSX varchar(155),
--     DiaChi varchar(255)
-- );

-- create table SP (
-- 	MaNSX varchar(255) primary key,
--     MaSP varchar(255),
--     Loai varchar(255)
-- );

-- create table PC (
-- 	MaSP_P varchar(255) primary key,
--     CPU varchar(255),
--     RAM varchar(255),
--     HD varchar(255),
--     Gia int
-- );

-- create table Laptop (
-- 	MaSP_L varchar(255) primary key,
--     CPU varchar(255),
--     RAM varchar(255),
--     HD varchar(255),
--     ManHinh varchar(255),
--     Gia int
-- );

SELECT n.TenNSX, COUNT(*) AS SoLuongPC
FROM NSX n
JOIN SP s ON n.MaNSX = s.MaNSX
JOIN PC p ON s.MaSP = p.MaSP_P
GROUP BY n.TenNSX;

SELECT DISTINCT l.MaSP_L, l.CPU  -- chỉ giữ lại một bản ghi duy nhất cho mỗi tập giá trị khác nhau trong các cột mà bạn chọn.
FROM Laptop l
JOIN PC p ON l.CPU = p.CPU
GROUP BY l.MaSP_L, l.CPU;

SELECT DISTINCT n.TenNSX
FROM NSX n
JOIN SP s ON n.MaNSX = s.MaNSX
LEFT JOIN PC p ON s.MaSP = p.MaSP_P
LEFT JOIN Laptop lp ON s.MaSP = lp.MaSP_L
WHERE (p.HD >= 1024 OR lp.HD >= 1024)
GROUP BY n.TenNSX;

SELECT n.TenNSX, AVG(lp.Gia) 
FROM NSX n
JOIN SP s ON n.MaNSX = s.MaNSX
JOIN LAPTOP lp ON s.MaSP = lp.MaSP_L
GROUP BY n.TenNSX;

SELECT n.TenNSX, p.MaSP_P, MAX(p.Gia)
FROM NSX n
JOIN SP s ON n.MaNSX = s.MaNSX
JOIN PC p ON s.MaSP = p.MaSP_P
GROUP BY n.MaNSX, p.MaSP_P;

SELECT n.TenNSX
FROM NSX n
JOIN SP s ON n.MaNSX = s.MaNSX
JOIN PC p ON s.MaSP = p.MaSP_P
GROUP BY n.TenNSX
HAVING MIN(CAST(REPLACE(p.RAM, 'GB','') AS UNSIGNED)) >= 8;











