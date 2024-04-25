Create or ALTER PROCEDURE sp_DoanhThuNam
AS 
BEGIN
    SELECT year(ngaychieu) as nam, COUNT(mave) AS "Số Lượng Vé", SUM(thanhtien) AS "Doanh Thu"
    FROM suatchieu
    JOIN ve ON suatchieu.masuatchieu = ve.masuatchieu
    GROUP BY  year(ngaychieu)  ;
END;
go;

Create or ALTER PROCEDURE sp_DoanhThuNgay
AS 
BEGIN
    SELECT ngaychieu as "Ngày chiếu", COUNT(mave) AS "Số Lượng Vé", SUM(thanhtien) AS "Doanh Thu"
    FROM suatchieu
    JOIN ve ON suatchieu.masuatchieu = ve.masuatchieu
    GROUP BY ngaychieu;
END;
go;

Create or ALTER PROCEDURE sp_DoanhThuThang
AS 
BEGIN
    SELECT CONCAT(month(ngaychieu),'-', year(ngaychieu)) as thang, COUNT(mave) AS "Số Lượng Vé", SUM(thanhtien) AS "Doanh Thu"
    FROM suatchieu
    JOIN ve ON suatchieu.masuatchieu = ve.masuatchieu
    GROUP BY  CONCAT(month(ngaychieu),'-', year(ngaychieu))  ;
END;
go;

Create or ALTER PROCEDURE sp_DoanhThuNhanVien
AS 
BEGIN
    select nhanvien , count(mave) as SoVe, Sum(thanhtien) as TongTien 
  from ve
  group by nhanvien
END;go;


create or ALTER PROCEDURE sp_hotphim
AS 
BEGIN
select suatchieu.maphim ,phim.tenphim,COUNT(mave) AS "Số Lượng Vé"
from suatchieu join phim on suatchieu.maphim = phim.maphim
				JOIN ve ON suatchieu.masuatchieu = ve.masuatchieu
group by suatchieu.maphim,phim.tenphim 
Order by COUNT(mave) desc
END;