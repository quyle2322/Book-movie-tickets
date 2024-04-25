drop database DA1_RapPhim
create database DA1_RapPhim
use DA1_RapPhim

create table nhanvien
(email varchar(50) primary key, 
matkhau varchar(30),
hoten nvarchar(30),
soDT varchar(13),
ngaysinh date,
gioitinh bit,
diachi nvarchar(50),
vaitro bit,
hinh varchar(10),
trangthai bit);

create table phim
(maphim char(10) primary key,
tenphim nvarchar(50),
thoiluong float,
theloai nvarchar(20),
namsx int,
nuocsx nvarchar(10),
noidung nvarchar(400),
poster nvarchar(50),
dienvien nvarchar(100),
trangthai bit);

create table phong
(maphong char(5) primary key,
soluongghe int, 
tinhtrang nvarchar(20),
trangthai bit);

create table suatchieu
(masuatchieu int identity primary key,
maphim char(10),
nguoitao varchar(50),
maphong char(5),
batdau time,
ketthuc time,
ngaychieu date,
foreign key (maphim) references phim(maphim) on delete no action on update cascade,
foreign key (nguoitao) references nhanvien(email) on delete no action on update cascade,
foreign key (maphong) references phong(maphong) on delete no action on update cascade);

create table khachhang
(email varchar(50) primary key,
matkhau varchar(30),
tenkh nvarchar(30),
sodt varchar(13),
diachi nvarchar(50),
thongtinthanhtoan varchar(50),
trangthai bit);

create table ve
(mave int,
masuatchieu int,
maghe varchar(5),
thanhtien float,
khachhang varchar(50),
nhanvien varchar(50),
thanhtoan bit,
foreign key (masuatchieu) references suatchieu(masuatchieu) on delete no action on update cascade,
foreign key (khachhang) references khachhang(email) on delete no action on update cascade,
foreign key (nhanvien) references nhanvien(email) on delete no action on update no action,
primary key (masuatchieu, maghe));


insert into khachhang values
('trunghieu12a326@gmail.com', '111111', N'Nguyễn Trung Hiếu', '0935186303', N'Long An', 'SCB: 070119736305',1 ),
('lieuphat1607@gmail.com', '111112', N'Liêu Vinh Phát', '0989321379', N'Sài Gòn', 'VCB:123456789',1 ),
('ngothiducnhu30052003@gmail.com', '111113', N'Ngô Thị Đức Nhu', '0123456789', N'Tiền Giang', 'VCB:1234789456',1 ),
('teonguyen123@gmail.com', '111114', N'Nguyễn Văn Tèo', '0935186304', N'Đà Lạt', 'VCB:123456789',1 ),
('lantran456@gmail.com', '111115', N'Trần Thị Kim Lan', '0935186305', N'Long An', 'VCB:123456789',1 ),
('hungdang789@gmail.com', '111116', N'Đặng Thanh Hùng', '0935186306', N'Sài Gòn', 'VCB:123456789',1 ),
('tungthanh100@gmail.com', '111117', N'Trần Thanh Tùng', '0935186307', N'Sài Gòn', 'VCB:123456789',1 ),
('jack5cu@gmail.com', '111118', N'Trịnh Trần Phương Tuấn', '0935186308', N'Sài Gòn', 'VCB:123456789',1 ),
('voibandon101@gmail.com', '1111119', N'Trần Anh Tú', '0935186309', N'Sài Gòn', 'VCB:123456789',1 ),
('ladymay102@gmail.com', '111120', N'Myra Trần', '0935186310', N'Sài Gòn', 'VCB:123456789',1 ),
('hungdam103@gmail.com', '111121', N'Đàm Vĩnh Hưng', '0935186311', N'Sài Gòn', 'VCB:123456789',1 ),
('giangvo201@gmail.com', '111122', N'Võ Vũ Trường Giang', '0935186312', N'Quảng Nam', 'VCB:123456789',1 ),
('thanhhuynh202@gmail.com', '111123', N'Huỳnh Trấn Thành', '0935186313', N'Sài Gòn', 'VCB:123456789',1 ),
('hoailinh14toi203@gmail.com', '111124', N'Võ Hoài Linh', '0935186314', N'Sài Gòn', 'VCB:123456789',1 ),
('spiderman301@gmail.com', '111125', N'Tôm Hà Land', '0935186315', N'Sài Gòn', 'VCB:123456789',1 ),
('ironman302@gmail.com', '111126', N'Tô Ny Sì Tách', '0935186316', N'Sài Gòn', 'VCB:123456789',1 ),
('captainamerica303@gmail.com', '111127', N'Ju Sai Mon', '0935186317', N'Sài Gòn', 'VCB:123456789',1 ),
('thehulk304@gmail.com', '111128', N' Jáck  Kirby', '0935186318', N'Sài Gòn', 'VCB:123456789',1 ),
('thethor305@gmail.com', '111129', N'Chris Hemsworth', '0935186319', N'Sài Gòn', 'VCB:123456789',1 ),
('thanos306@gmail.com', '111130', N'Josh Brolin', '0935186320', N'Sài Gòn', 'VCB:123456789',1 );

insert into NhanVien values
('hieuntps27619@fpt.edu.vn', '111131', N'Nguyễn Trung Hiếu', '0935186303','2003-07-21',1, N'Long An', 1,'nv1.png',1 ),
('phatlvps27456@fpt.edu.vn', '111132', N'Liêu Vinh Phát', '0989321379', '2003-07-21',1, N'Long An', 1,'nv1.png',1  ),
('nhuntdps27430@fpt.edu.vn', '111133', N'Ngô Thị Đức Nhu', '0123456789', '2003-07-21',1, N'Long An',1,'nv1.png',1  ),
('hocntps27837@fpt.edu.vn', '111134', N'Nguyễn Tiến Học', '0935186321', '2003-07-21',1, N'Long An',1,'nv1.png',1  ),
('hieutruong001@fpt.edu.vn', '111135', N'Thầy Hiệu Trưởng', '0935186322', '2003-07-21',1, N'Long An',1,'nv1.png',1 ),
('hieupho002@fpt.edu.vn', '111136', N'Thầy Hiệu Phó', '0935186323', '2003-07-21',1, N'Long An',1,'nv1.png',1 ),
('truongcntt003@fpt.edu.vn', '111137', N'Trưởng Bộ Môn CNTT','0935186324', '2003-07-21',1, N'Long An',1,'nv1.png',1 ),
('truongmkts004@fpt.edu.vn', '111138', N'Trưởng Bộ Môn MaketingandSales', '0935186325', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('truongvovinam005@fpt.edu.vn', '111139', N'Trưởng Bộ Môn Vovinam', '0935186326', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('truongdlks006@fpt.edu.vn', '111140', N'Trưởng Bộ Môn DL KS', '0935186327', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('truongtkdo007@fpt.edu.vn', '111141', N'Trưởng Bộ Môn TKDH', '0935186328', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('luubo100@fpt.edu.vn', '111142', N'Lưu Bá Ôn', '0935186329', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('chungnn101@fpt.edu.vn', '111143', N'Chung Nguyên Chương',  '0935186330','2003-07-21',1, N'Long An',1,'nv1.png',1),
('truongp200@fpt.edu.vn', '111144', N'Trương Phi', '0935186331', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('vuqt201@fpt.edu.vn', '111145', N'Trương Quan Vũ', '0935186332', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('trieuvan202@fpt.edu.vn', '111146', N'Triệu Tử Long', '0935186333', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('bolu300@fpt.edu.vn', '111147', N'Lữ Bố', '0935186334', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('thuyendieu301@fpt.edu.vn', '111148', N'Điêu Thuyền', '0935186335', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('tracdong302@fpt.edu.vn', '111149', N'Đổng Trác', '0935186336', '2003-07-21',1, N'Long An',1,'nv1.png',1),
('thieuvien303@fpt.edu.vn', '111150', N'Viên Thiệu', '0935186337', '2003-07-21',1, N'Long An',1,'nv1.png',1);

insert into Phim values
('PHIM001', N'Nhà bà Nữ',102.0,N'Tình cảm',2023,N'Việt Nam',N'Nội dung phim xoay quanh câu chuyện gia đình nhà bà Nữ - một gia đình bán bánh canh cua. Mâu thuẫn giữa bà Nữ với Nhi (con gái bà Nữ) là yếu tố tạo nên sự kịch tính cho phim. Mâu thuẫn này được giải quyết qua hàng loạt sự kiện, biến cố, và để lại nhiều bài học quý giá về tình cảm gia đình.','phim01.jpg',N'Trấn Thành, Lê Giang, NSND Ngọc Giàu',1),
('PHIM002', N'BABYLON',188.0,N'Hài',2023,N'Mỹ',N'Bộ phim này tập trung khai thác những thăng trầm của nhiều nhân vật trong quá trình chuyển đổi của Hollywood, từ phim câm sang phim có tiếng (cuối những năm 1920).','phim02.jpg',N'Brad Pitt, Diego Calva, Margot Robbie',1),
('PHIM003', N'Mèo Đi Hia',103.0,N'Hài',2022,N'Mỹ',N'Bộ phim này kể về cuộc phiêu lưu của Puss, một chú mèo đam mê thám hiểm và đầy khí chất.','phim03.jpg',N'Antonio Banderas, Salma Hayek',1),
('PHIM004', N'Bay vào tử địa',108.0,N'Hành động',2023,N'Anh',N'Trong một chuyến bay của cơ trưởng Brodie, máy bay bất ngờ bị sét đánh và rơi xuống một quần đảo bí ẩn do phiến quân chiếm đóng. Các thành viên trên máy bay buộc phải chiến đấu cùng nhau để giành lấy sự sống. Điều này buộc cơ trưởng Brodie phải hợp tác với một kẻ sát nhân. Liệu họ có thành công trong cuộc chiến sống còn này?','phim04.jpg',N'Mike Colter, Gerard Butler',1),
('PHIM005', N'Quý Ông Số Đỏ',124.0,N'Hành động',2023,N'Hàn Quốc',N'Nội dung phim xoay quanh nhân vật chính Ji Hyun Soo (do Ju Ji-hoon đảm vai), người điều hành một công ty thám tử tư. Trong một vụ án, anh ta bị tình nghi là nghi phạm bắt cóc khách hàng của mình. Nhưng trên đường áp giải về sở cảnh sát, một vụ tai nạn xảy ra và khiến công tố viên bị thương nặng. Ji Hyun Soo bị nhầm thành công tố viên, và anh ta lợi dụng điều này để minh oan cho chính mình.','phim05.jpg',N'Ju Ji-hoon, Park Sung-woong, Kang Hong-suk',1),
('PHIM006', N'Thanh âm tình đầu',115.0,N'Tình cảm',2023,N'Hàn Quốc',N'Trong một lần, hai người họ vô tình gặp nhau vào đêm có nguyệt thực toàn phần. Từ đó, hai người thường xuyên gặp nhau và liên lạc xuyên thời gian. Mối quan hệ giữa họ lớn dần, khiến nửa kia trở thành phần không thể thiếu của người còn lại.','phim06.jpg',N'Yi-Hyun Cho, Yeo Jin-gu và Kim Hye-yoon',1),
('PHIM007', N'“NÓ” ở trong rừng',107.0,N'Kinh dị',2023,N'Nhật Bản',N'Phim lấy bối cảnh tại khu rừng già Fukushima, nơi xảy ra nhiều câu chuyện kỳ lạ, bí ẩn. Các cảnh quay chân thực trong khu rừng và các vụ án đẫm máu hứa hẹn khiến người xem hứng thú.','phim07.jpg',N'Noriko Eguchi, Aiba Masaki',1),
('PHIM008', N'Siêu Lừa Gặp Siêu Lầy',112.0,N'Hài',2023,N'Việt Nam',N'Thuộc phong cách hành động – hài hước với các “cú lừa” thông minh và lầy lội đến từ bộ đôi Tú (Anh Tú) và Khoa (Mạc Văn Khoa), Siêu Lừa Gặp Siêu Lầy của đạo diễn Võ Thanh Hòa theo chân của Khoa – tên lừa đảo tầm cỡ “quốc nội” đến đảo ngọc Phú Quốc với mong muốn đổi đời.','phim08.jpg',N'Anh Tú, Mạc Văn Khoa',1),
('PHIM009', N'Trận Chiến Thời Tiền Sử',93.0,N'Hành động',2023,N'Mỹ',N'Sau cú va chạm thảm khốc, tàu vũ trụ của Mills bay thẳng đến một hành tinh không xác định. Với vốn kiến thức sẵn có và trực giác nhạy bén của mình, Mills nhận ra nơi đây chính là Trái Đất của 65 triệu năm trước.','phim09.jpg',N'Adam Driver, Ariana Greenblatt',1),
('PHIM010', N'John Wick: Chapter 4',130.0,N'Hành Động',2023,N'Mỹ',N'Tiếp nối cuộc chiến đã được khởi động trong Chapter 3 – Parabellum, John Wick Chapter 4 sẽ mang đến một câu chuyện bom tấn siêu lôi cuốn. Người hâm mộ sẽ thấy được tình trạng John Wick đang sống dở chết dở khi phải đối đầu với sát thủ toàn cầu.','phim10.jpg',N'Keanu Reeves, Donnie Yen, Bill Skarsgård',1)

insert into Phong values 
('PH001',60,N'Còn ghế',1),
('PH002',60,N'Còn ghế',1),
('PH003',60,N'Còn ghế',1),
('PH004',60,N'Còn ghế',1),
('PH005',60,N'Còn ghế',1),
('PH006',60,N'Còn ghế',1),
('PH007',60,N'Còn ghế',1),
('PH008',60,N'Còn ghế',1),
('PH009',60,N'Còn ghế',1),
('PH010',60,N'Còn ghế',1),
('PH011',60,N'Còn ghế',1),
('PH012',60,N'Còn ghế',1),
('PH013',60,N'Còn ghế',1),
('PH014',60,N'Còn ghế',1),
('PH015',60,N'Còn ghế',1);

insert into SuatChieu values
('PHIM007','hieuntps27619@fpt.edu.vn','PH003','8:00','10:00','2023-01-14'),
('PHIM007','hieuntps27619@fpt.edu.vn','PH003','10:00','12:00','2023-01-14'),
('PHIM007','hieuntps27619@fpt.edu.vn','PH003','13:00','15:00','2023-01-14'),
('PHIM007','hieuntps27619@fpt.edu.vn','PH003','15:00','17:00','2023-01-14'),
('PHIM007','hieuntps27619@fpt.edu.vn','PH003','17:00','19:00','2023-01-14'),
('PHIM001','hieuntps27619@fpt.edu.vn','PH002','8:00','10:00','2023-01-14'),
('PHIM001','hieuntps27619@fpt.edu.vn','PH002','10:00','12:00','2023-01-14'),
('PHIM001','hieuntps27619@fpt.edu.vn','PH002','13:00','15:00','2023-01-14'),
('PHIM001','hieuntps27619@fpt.edu.vn','PH002','15:00','17:00','2023-01-14'),
('PHIM001','hieuntps27619@fpt.edu.vn','PH002','17:00','19:00','2023-01-14'),
('PHIM003','phatlvps27456@fpt.edu.vn','PH001','8:00','10:00','2023-05-24'),
('PHIM003','phatlvps27456@fpt.edu.vn','PH001','10:00','12:00','2023-05-24'),
('PHIM003','phatlvps27456@fpt.edu.vn','PH001','13:00','15:00','2023-05-24'),
('PHIM003','phatlvps27456@fpt.edu.vn','PH001','15:00','17:00','2023-05-24'),
('PHIM003','phatlvps27456@fpt.edu.vn','PH001','17:00','19:00','2023-05-24'),
('PHIM006','nhuntdps27430@fpt.edu.vn','PH004','8:00','10:00','2023-07-12'),
('PHIM006','nhuntdps27430@fpt.edu.vn','PH004','10:00','12:00','2023-07-12'),
('PHIM006','nhuntdps27430@fpt.edu.vn','PH004','13:00','15:00','2023-07-12'),
('PHIM006','nhuntdps27430@fpt.edu.vn','PH004','15:00','17:00','2023-07-12'),
('PHIM006','nhuntdps27430@fpt.edu.vn','PH004','17:00','19:00','2023-07-12');

insert into Ve values 
(1,2,'A01',65.000,'hoailinh14toi203@gmail.com','hieuntps27619@fpt.edu.vn',1),
(2,2,'A06',65.000,'teonguyen123@gmail.com','hieuntps27619@fpt.edu.vn',1),
(3,2,'A07',65.000,'ironman302@gmail.com','hieuntps27619@fpt.edu.vn',1),
(4,2,'D01',65.000,'thehulk304@gmail.com','hieuntps27619@fpt.edu.vn',1),
(5,2,'D02',65.000,'lantran456@gmail.com','hieuntps27619@fpt.edu.vn',1),
(6,3,'D01',65.000,'tungthanh100@gmail.com','hieuntps27619@fpt.edu.vn',1),
(7,3,'F06',65.000,'ngothiducnhu30052003@gmail.com','hieuntps27619@fpt.edu.vn',1),
(8,3,'F07',65.000,'lieuphat1607@gmail.com','hieuntps27619@fpt.edu.vn',1),
(9,4,'C01',65.000,'thehulk304@gmail.com','hieuntps27619@fpt.edu.vn',1),
(10,5,'C02',65.000,'thanos306@gmail.com','hieuntps27619@fpt.edu.vn',1),
(11,15,'A01',65.000,'hoailinh14toi203@gmail.com','phatlvps27456@fpt.edu.vn',1),
(12,15,'A06',65.000,'teonguyen123@gmail.com','phatlvps27456@fpt.edu.vn',1),
(13,15,'A07',65.000,'ironman302@gmail.com','phatlvps27456@fpt.edu.vn',1),
(14,15,'D01',65.000,'thehulk304@gmail.com','phatlvps27456@fpt.edu.vn',1),
(15,16,'D02',65.000,'lantran456@gmail.com','nhuntdps27430@fpt.edu.vn',1),
(16,16,'D01',65.000,'tungthanh100@gmail.com','nhuntdps27430@fpt.edu.vn',1);