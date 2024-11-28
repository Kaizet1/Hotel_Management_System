--tao moi database
create database myDatabase2
go
use myDatabase2
go
--tao cac table
--khach hang
create table KhachHang(
	maKH varchar(20) primary key ,
	hoTen nvarchar(100) not null,
	diaChi nvarchar(200) not null,
	SDT varchar(15) not null,
	soCCCD varchar(20) not null unique,
	email varchar(30) ,
	trangThai int 
)

--nhan vien
create table NhanVien (
	maNV varchar(20) primary key, 
	hoTen nvarchar(20) not null,
	chucVu nvarchar(20) not null, 
	ngaySinh Date not null,
	ngayVaoLam Date not null check(ngayVaoLam <= getDate() ),
	SDT varchar(20) not null, 
	diaChi nvarchar(200) not null, 
	email varchar(40) not null unique,
	luongCoBan float, 
	heSoLuong float
	
)
--tai khoan
create table TaiKhoan (
	tenDN nvarchar(20) primary key,
	matKhau varchar(40) not null unique,
	maNV varchar(20)
)
--loai phong
create table LoaiPhong(
	tenLoai nvarchar(50) primary key,
	moTa nvarchar(200)
)

--phong
create table Phong(
	maPhong varchar(20) primary key,
	loaiPhong nvarchar(50),
	tenPhong nvarchar(20) not null,
	giaPhong float check(giaPhong >= 0),
	tinhTrang int not null check(tinhTrang in(0,1,2,3)),
	soNguoi int not null check(soNguoi > 0),
	moTa nvarchar(200),
)

--dich vu
create table DichVu(
	maDV varchar(20) primary key, 
	tenDV nvarchar(20) not null, 
	giaDV float not null check(giaDV >0), 
	moTa nvarchar(200)not null,
)

--thue VAT
create table ThueVAT(
	maSoThue int primary key,
	mucThue float not null,
	ngayApDung date not null,
	moTa nvarchar(200)
)

--phieu dat phong
create table PhieuDatPhong(
	maPDP varchar(20) primary key,
	maKH varchar(20),
	maNV varchar(20),
	maPhong varchar(20),
	tinhTrangPDP int not null,
	ngayDat date not null check(ngayDat <= getDate()),
	ngayDen datetime not null ,
	ngayDi datetime not null, 
)

--phieu giam gia
create table PhieuGiamGia(
	maPGG varchar(20) primary key,
	tenPGG nvarchar(50), 
	ngayBatDau date not null,
	ngayKetThuc date not null,
	dieuKienApDung nvarchar(200),
	luotSuDung int not null,
	mucGiamGia float not null,
)

--chi tiet phieu giam gia
CREATE TABLE ChiTietPGG (
    maPGG VARCHAR(20),
    maPhong VARCHAR(20),             
    ngayApDung DATE NOT NULL, 
    moTa nvarchar(200) not null,
    PRIMARY KEY(maPGG, maPhong)
)

--hoa don 
create table HoaDon(
	maHD varchar(20) primary key,
	ngayLapHD date not null,
	ngayNhanPhong date not null ,
	ngayTraPhong date,
	soPhongDat int check(soPhongDat > 0),
	maKH varchar(20),
	maNV varchar(20),
	maPhong varchar(20),
	maSoThue int, 
	foreign key (maKH) references KhachHang(maKH),
	foreign key (maNV) references NhanVien(maNV),
)
--chi tiet hoa don
CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(20),
    maPhong VARCHAR(20),
	maDV varchar(20),
	thoiGianThuePhong Date,
	soLuongGiuongPhu int, 
	soLuongDV int,
	soLuongPhong int
    PRIMARY KEY(maHD, maPhong, maDV)
)
go



ALTER TABLE TaiKhoan
ADD CONSTRAINT FK_TaiKhoan_NhanVien
FOREIGN KEY (maNV) REFERENCES NhanVien(maNV);

ALTER TABLE Phong
ADD CONSTRAINT FK_Phong_LoaiPhong
FOREIGN KEY (loaiPhong) REFERENCES LoaiPhong(tenLoai);

ALTER TABLE HoaDon
ADD CONSTRAINT CK__HoaDon__ngayNhan__59FA5E80 CHECK (ngayNhanPhong <= ngayLapHD);
ALTER TABLE HoaDon 
ADD CONSTRAINT CK__HoaDon__ngayLapH__59063A47 CHECK (ngayLapHD <= GETDATE());

alter table HoaDon
ADD CONSTRAINT FK_HoaDon_Thue
FOREIGN KEY (maSoThue) REFERENCES ThueVAT(maSoThue);

Alter table PhieuDatPhong
ADD CONSTRAINT FK_PDP_KhachHang 
FOREIGN KEY (maKH) REFERENCES KhachHang(maKH);

alter table PhieuDatPhong
ADD CONSTRAINT FK_PDP_NhanVien
FOREIGN KEY (maNV) REFERENCES NhanVien(maNV);

alter table ChiTietPGG
ADD CONSTRAINT FK_CTPGG_Phong
FOREIGN KEY (maPhong) REFERENCES Phong(maPhong);

alter table ChiTietPGG
ADD CONSTRAINT FK_CTGG_PGG
FOREIGN KEY (maPGG) REFERENCES PhieuGiamGia(maPGG);

alter table PhieuDatPhong
ADD CONSTRAINT FK_PDP_Phong
FOREIGN KEY (maPhong) REFERENCES Phong(maPhong);

alter table ChiTietHoaDon
ADD CONSTRAINT FK_CTHD_Phong
FOREIGN KEY (maPhong) REFERENCES Phong(maPhong);
alter table ChiTietHoaDon
ADD CONSTRAINT FK_CTHD_HoaDon
FOREIGN KEY (maHD) REFERENCES HoaDon(maHD);
alter table ChiTietHoaDon
ADD CONSTRAINT FK_CTHD_DV
FOREIGN KEY (maDV) REFERENCES DichVu(maDV);

--chen du lieu
--khach hang
insert into KhachHang(maKH, hoTen, diaChi, SDT, soCCCD,email, trangThai)
VALUES
('KH-001', N'Trần Quốc Khánh', N'123 Lý Thường Kiệt, Quận 1, TP.HCM', '0909123456', '123456789012', 'tranquockhanh@gmail.com',  1),
('KH-002', N'Nguyễn Thị Thanh Hương', N'456 Nguyễn Trãi, Quận 5, TP.HCM', '0908123456', '234567890123', 'nguyenthihuong@gmail.com',  1),
('KH-003', N'Lê Hoàng Nam', N'789 Võ Văn Tần, Quận 3, TP.HCM', '0307123456', '345678901234', 'lehoangnam@gmail.com',  1),
('KH-004', N'Phan Thị Bích Ngọc', N'12 Cách Mạng Tháng 8, Quận 10, TP.HCM', '0906123456', '456789012345', 'phanbichngoc@gmail.com',  1),
('KH-005', N'Huỳnh Ngọc Minh', N'321 Lê Lợi, Quận 1, TP.HCM', '0905123456', '567890123456', 'huynhngocminh@gmail.com',  1),
('KH-006', N'Vũ Văn Hùng', N'654 Trần Hưng Đạo, Quận 1, TP.HCM', '0904123456', '678901234567', 'vuvanhung@gmail.com',  1),
('KH-007', N'Đinh Thị Thu Trang', N'87 Hai Bà Trưng, Quận 3, TP.HCM', '0903123456', '789012345678', 'dinhthutrang@gmail.com',  1),
('KH-008', N'Nguyễn Phúc Thịnh', N'123 Pasteur, Quận 1, TP.HCM', '0902123456', '890123456789', 'nguyenphucthinh@gmail.com',  1),
('KH-009', N'Trịnh Công Dũng', N'45 Nguyễn Huệ, Quận 1, TP.HCM', '0901123456', '901234567890', 'trinhcongdung@gmail.com',  1),
('KH-010', N'Phạm Văn Thắng', N'98 Đường 3 Tháng 2, Quận 10, TP.HCM', '0711123456', '012345678901', 'phamvanthang@gmail.com',  1),
('KH-011', N'Lý Thị Minh Châu', N'123 Bạch Đằng, Quận Bình Thạnh, TP.HCM', '0312123456', '111234567890', 'lyminhchau@gmail.com',  1),
('KH-012', N'Bùi Quang Vinh', N'567 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', '0913123456', '222345678901', 'buiquangvinh@gmail.com',  1),
('KH-013', N'Ngô Văn Hải', N'432 Xô Viết Nghệ Tĩnh, Quận Bình Thạnh, TP.HCM', '0914123456', '333456789012', 'ngovanhai@gmail.com',  1),
('KH-014', N'Đoàn Thị Mỹ Linh', N'876 Nguyễn Văn Trỗi, Quận Phú Nhuận, TP.HCM', '0315123456', '444567890123', 'doanmylinh@gmail.com',  1),
('KH-015', N'Phan Thanh Hà', N'12 Hoàng Sa, Quận 3, TP.HCM', '0916123456', '555678901234', 'phanthanhha@gmail.com', 1),
('KH-016', N'Trương Quốc Bảo', N'34 Trường Sa, Quận Phú Nhuận, TP.HCM', '0717123456', '666789012345', 'truongquocbao@gmail.com',  1),
('KH-017', N'Hoàng Thanh Tú', N'789 Lê Hồng Phong, Quận 10, TP.HCM', '0918123456', '777890123456', 'hoangthanhtu@gmail.com',  1),
('KH-018', N'Võ Minh Tuấn', N'23 Nguyễn Đình Chiểu, Quận 3, TP.HCM', '0919123456', '888901234567', 'vomintuan@gmail.com',  1),
('KH-019', N'Đỗ Thị Hồng Vân', N'654 Võ Thị Sáu, Quận 3, TP.HCM', '0720123456', '999012345678', 'dothihongvan@gmail.com',  1),
('KH-020', N'Lê Văn Tài', N'76 Nam Kỳ Khởi Nghĩa, Quận 1, TP.HCM', '0921123456', '000123456789', 'levantai@gmail.com',  1);


--nhan vien
INSERT INTO NhanVien (maNV, hoTen, chucVu, ngaySinh, ngayVaoLam, SDT, diaChi, email, luongCoBan, heSoLuong)
VALUES
('ADMIN', 'ADMIN', N'Admin', '', '', '', '', '', 0, 0),
('20-AB12', N'Nguyễn Văn Thiện', N'Lễ tân', '1990-01-15', '2020-05-20', '0901234567', N'123 Đường 1, Quận 1, TP.HCM', 'nguyenthien@gmail.com', 5000000, 2.5),
('20-CD34', N'Trần Thị Phương', N'Lễ tân', '1985-02-10', '2020-03-15', '0902345678', N'456 Đường 2, Quận 2, TP.HCM', 'tranphuong@gmail.com', 6000000, 3.0),
('19-EF56', N'Lê Văn Bình', N'Lễ tân', '1992-03-25', '2019-04-18', '0903456789', N'789 Đường 3, Quận 3, TP.HCM', 'lebinh@gmail.com', 5500000, 2.8),
('21-GH78', N'Phạm Thị Hương', N'Lễ tân', '1988-04-30', '2021-01-10', '0904567890', N'321 Đường 4, Quận 4, TP.HCM', 'phamhuong@gmail.com', 6200000, 3.2),
('22-IJ90', N'Ngô Văn Tài', N'Lễ tân', '1995-05-05', '2022-02-22', '0905678901', N'654 Đường 5, Quận 5, TP.HCM', 'ngotai@gmail.com', 5800000, 2.9),
('20-KL12', N'Trương Thị Lan', N'Lễ tân', '1987-06-15', '2020-07-25', '0906789012', N'987 Đường 6, Quận 6, TP.HCM', 'truonglan@gmail.com', 6400000, 3.5),
('19-MN34', N'Vũ Văn Khải', N'Lễ tân', '1991-07-20', '2019-08-30', '0907890123', N'135 Đường 7, Quận 7, TP.HCM', 'vukhai@gmail.com', 6000000, 3.0),
('17-OP56', N'Nguyễn Thị Mai', N'Lễ tân', '1984-08-12', '2017-09-01', '0908901234', N'246 Đường 8, Quận 8, TP.HCM', 'nguyenmai@gmail.com', 6500000, 3.1),
('21-QR78', N'Lê Hoàng Minh', N'Lễ tân', '1990-09-22', '2021-10-15', '0909012345', N'357 Đường 9, Quận 9, TP.HCM', 'leminh@gmail.com', 5100000, 2.6),
('18-ST90', N'Phan Thị Ngọc', N'Lễ tân', '1989-10-05', '2018-11-20', '0900123456', N'468 Đường 10, Quận 10, TP.HCM', 'phangoc@gmail.com', 5700000, 3.4 ),
('20-UV12', N'Nguyễn Văn Đạt', N'Lễ tân', '1993-11-18', '2020-01-30', '0901234568', N'579 Đường 11, Quận 11, TP.HCM', 'nguyendat@gmail.com', 5000000, 2.7),
('17-WX34', N'Trần Thị Hạnh', N'Lễ tân', '1986-12-25', '2017-12-22', '0902345679', N'680 Đường 12, Quận 12, TP.HCM', 'tranhanh@gmail.com', 6200000, 3.3),
('21-YZ56', N'Lê Văn Nam', N'Lễ tân', '1994-01-10', '2021-04-04', '0903456780', N'791 Đường 13, Quận 1, TP.HCM', 'levannam@gmail.com', 5900000, 2.8),
('19-AB78', N'Phạm Thị Kiều', N'Lễ tân', '1983-02-20', '2019-05-12', '0904567891', N'802 Đường 14, Quận 2, TP.HCM', 'phamkieul@gmail.com', 6600000, 3.2),
('22-CD90', N'Ngô Văn An', N'Lễ tân', '1992-03-15', '2022-08-14', '0905678902', N'913 Đường 15, Quận 3, TP.HCM', 'ngoan@gmail.com', 5200000, 2.9),
('20-EF12', N'Trương Thị Tuyết', N'Lao công', '1988-04-12', '2020-07-21', '0906789013', N'024 Đường 16, Quận 4, TP.HCM', 'truongtuyet@gmail.com', 6400000, 3.1),
('19-GH34', N'Vũ Văn Sơn', N'Lao công', '1995-05-18', '2019-10-05', '0907890124', N'135 Đường 17, Quận 5, TP.HCM', 'vuson@gmail.com', 5800000, 2.6),
('18-IJ56', N'Nguyễn Thị Hoa', N'Quản lý', '1984-06-25', '2018-11-15', '0908901235', N'246 Đường 18, Quận 6, TP.HCM', 'nguyenhoa@gmail.com', 6500000, 3.5),
('21-KL78', N'Lê Hoàng Dương', N'Lễ tân', '1991-07-22', '2021-10-15', '0909012346', N'357 Đường 19, Quận 7, TP.HCM', 'leduong@gmail.com', 6000000, 3.0),
('17-MN90', N'Phan Văn Thắng', N'Lễ tân', '1985-12-30', '2017-12-01', '0900123456', N'246 Đường 20, Quận 8, TP.HCM', 'phanthang@gmail.com', 6700000, 3.8);

--tai khoan
INSERT INTO TaiKhoan (tenDN, matKhau, maNV)
VALUES
('admin', '1', 'ADMIN'),
('nguyenthiện', 'H@ppy1', '20-AB12'),
('tranphuong', 'S3cure!2', '20-CD34'),
('lebinh', 'F#resh3', '19-EF56'),
('phamhuong', 'B@kery4', '21-GH78'),
('ngotai', 'C0ffee%5', '22-IJ90'),
('truonglan', 'M!lk6', '20-KL12'),
('vukhai', 'Choco^7', '19-MN34'),
('nguyenmai', 'F@ir8', '17-OP56'),
('leminh', 'D@rk9', '21-QR78'),
('phangoc', 'G!bber0', '18-ST90'),
('nguyendat', 'J@zz1', '20-UV12'),
('tranhanh', 'T@co2', '17-WX34'),
('levannam', 'S@lt3', '21-YZ56'),
('phamkieul', 'V@le4', '19-AB78'),
('ngoan', 'Q@uick5', '22-CD90'),
('truongtuyet', 'W@v3s6', '20-EF12'),
('vuson', 'T@il7', '19-GH34'),
('nguyenhoa', 'H@rd8', '18-IJ56'),
('leduong', 'B@ss9', '21-KL78'),
('phanthang', 'S@wer0', '17-MN90');

--loai phong
INSERT INTO LoaiPhong (tenLoai, moTa)
VALUES
(N'Phòng Đơn', N'Phòng đơn dành cho một người.'),
(N'Phòng Đôi', N'Phòng dành cho hai người.'),
(N'Phòng Gia Đình', N'Phòng dành cho gia đình từ ba người đến năm người.'),
(N'Phòng VIP', N'Phòng VIP sang trọng với dịch vụ hoàn hảo.');

--phong
INSERT INTO Phong (maPhong, tenPhong, giaPhong, tinhTrang, soNguoi, moTa, loaiPhong)
VALUES
('A001', N'Phòng Đơn A1', 500000, 0, 1, N'Phòng đơn cho 1 người.', N'Phòng Đơn'),
('A002', N'Phòng Đơn A2', 550000, 1, 1, N'Phòng đơn với tầm nhìn đẹp.', N'Phòng Đơn'),
('A003', N'Phòng Đôi A3', 800000, 2, 2, N'Phòng đôi cho 2 người.', N'Phòng Đôi'),
('A004', N'Phòng Đôi A4', 850000, 0, 2, N'Phòng đôi tiện nghi.', N'Phòng Đôi'),
('A005', N'Phòng Gia Đình A5', 1200000, 3, 4, N'Phòng gia đình cho 4 người.', N'Phòng Gia Đình'),
('A006', N'Phòng VIP A6', 1500000, 0, 2, N'Phòng VIP với đầy đủ tiện nghi.', N'Phòng VIP'),
('B001', N'Phòng Đơn B1', 520000, 0, 1, N'Phòng đơn yên tĩnh.', N'Phòng Đơn'),
('B002', N'Phòng Đơn B2', 570000, 1, 1, N'Phòng đơn gần hồ bơi.', N'Phòng Đơn'),
('B003', N'Phòng Đôi B3', 820000, 2, 2, N'Phòng đôi có ban công.', N'Phòng Đôi'),
('B004', N'Phòng Đôi B4', 870000, 0, 2, N'Phòng đôi thoáng mát.', N'Phòng Đôi'),
('B005', N'Phòng Gia Đình B5', 1250000, 3, 4, N'Phòng gia đình rộng rãi.', N'Phòng Gia Đình'),
('B006', N'Phòng VIP B6', 1550000, 0, 2, N'Phòng VIP sang trọng.', N'Phòng VIP'),
('C001', N'Phòng Đơn C1', 540000, 0, 1, N'Phòng đơn với nội thất hiện đại.', N'Phòng Đơn'),
('C002', N'Phòng Đơn C2', 590000, 1, 1, N'Phòng đơn gần khu ăn uống.', N'Phòng Đơn'),
('C003', N'Phòng Đôi C3', 840000, 2, 2, N'Phòng đôi phong cách cổ điển.', N'Phòng Đôi'),
('C004', N'Phòng Đôi C4', 890000, 0, 2, N'Phòng đôi với dịch vụ cao cấp.', N'Phòng Đôi'),
('C005', N'Phòng Gia Đình C5', 1300000, 3, 4, N'Phòng gia đình tiện nghi.', N'Phòng Gia Đình'),
('C006', N'Phòng VIP C6', 1600000, 0, 2, N'Phòng VIP riêng biệt.', N'Phòng VIP'),
('D001', N'Phòng Đơn D1', 530000, 0, 1, N'Phòng đơn thanh lịch.', N'Phòng Đơn'),
('D002', N'Phòng Đơn D2', 580000, 1, 1, N'Phòng đơn ấm cúng.', N'Phòng Đơn'),
('D003', N'Phòng Đôi D3', 830000, 2, 2, N'Phòng đôi có view đẹp.', N'Phòng Đôi'),
('D004', N'Phòng Đôi D4', 880000, 0, 2, N'Phòng đôi tiện lợi.', N'Phòng Đôi'),
('D005', N'Phòng Gia Đình D5', 1350000, 3, 4, N'Phòng gia đình thoải mái.', N'Phòng Gia Đình'),
('D006', N'Phòng VIP D6', 1650000, 0, 2, N'Phòng VIP với phong cách độc đáo.', N'Phòng VIP'),
('E001', N'Phòng Đơn E1', 510000, 0, 1, N'Phòng đơn thanh tĩnh.', N'Phòng Đơn'),
('E002', N'Phòng Đơn E2', 560000, 1, 1, N'Phòng đơn với giường đôi.', N'Phòng Đơn'),
('E003', N'Phòng Đôi E3', 810000, 2, 2, N'Phòng đôi có bồn tắm.', N'Phòng Đôi'),
('E004', N'Phòng Đôi E4', 860000, 0, 2, N'Phòng đôi với minibar.', N'Phòng Đôi'),
('E005', N'Phòng Gia Đình E5', 1400000, 3, 4, N'Phòng gia đình tiện lợi.', N'Phòng Gia Đình'),
('E006', N'Phòng VIP E6', 1700000, 0, 2, N'Phòng VIP với dịch vụ đặc biệt.', N'Phòng VIP');

--dich vu
INSERT INTO DichVu (maDV, tenDV, moTa, giaDV) VALUES
('24AB1C', N'Giặt ủi', N'Dịch vụ giặt ủi quần áo cho khách', 150000.0),
('24D3F2', N'Thức ăn tại phòng', N'Dịch vụ phục vụ đồ ăn ngay tại phòng', 200000.0),
('24G5H4', N'Mát xa', N'Dịch vụ mát xa thư giãn', 300000.0),
('24J8K9', N'Đưa đón sân bay', N'Dịch vụ đưa đón khách từ sân bay', 500000.0),
('24L2N7', N'Tiệc tùng', N'Dịch vụ tổ chức tiệc tại khách sạn', 1000000.0),
('24P4Q6', N'Spa', N'Dịch vụ chăm sóc sắc đẹp tại spa', 700000.0),
('24R9T1', N'Giải trí', N'Dịch vụ giải trí tại khách sạn', 400000.0),
('24V6X8', N'Cho thuê xe', N'Dịch vụ cho thuê xe cho khách', 250000.0),
('24Z3M2', N'Hướng dẫn du lịch', N'Dịch vụ hướng dẫn tham quan các địa điểm', 600000.0),
('24B4S5', N'Sự kiện', N'Tổ chức sự kiện cho khách hàng', 1200000.0),
('24C8D0', N'Bể bơi', N'Sử dụng bể bơi trong khuôn viên khách sạn', 100000.0),
('24E6F3', N'Phòng tập gym', N'Sử dụng phòng tập gym tại khách sạn', 80000.0),
('24K1L8', N'Dịch vụ tiễn khách', N'Tiễn khách ra sân bay', 300000.0),
('24N9O4', N'Đặt tour', N'Dịch vụ đặt tour du lịch cho khách', 200000.0),
('24T5U0', N'Dịch vụ đặt bàn ăn', N'Đặt bàn ăn tại nhà hàng của khách sạn', 150000.0),
('24X9Z3', N'Chăm sóc thú cưng', N'Dịch vụ chăm sóc thú cưng cho khách', 350000.0);

--phieu giam gia
INSERT INTO PhieuGiamGia (maPGG, ngayBatDau, ngayKetThuc, dieuKienApDung, luotSuDung, mucGiamGia, tenPGG) VALUES
('PGG2024001', '2024-09-01', '2024-09-30', N'Giảm giá cho phòng đơn', 100, 10.0, N'Giảm giá phòng đơn'),
('PGG2024002', '2024-09-01', '2024-09-30', N'Giảm giá cho phòng đôi', 50, 15.0, N'Giảm giá phòng đôi'),
('PGG2024003', '2024-09-01', '2024-10-30', N'Giảm giá cho phòng gia đình', 200, 20.0, N'Giảm giá phòng gia đình'),
('PGG2024004', '2024-09-01', '2024-09-30', N'Giảm giá cho phòng VIP', 150, 25.0, N'Giảm giá phòng VIP'),
('PGG2024005', '2024-09-10', '2024-11-20', N'Giảm giá cho đặt phòng tập thể', 80, 30.0, N'Giảm giá đặt phòng tập thể');

--chi tiet phieu giam gia
INSERT INTO ChiTietPGG (maPGG, maPhong, ngayApDung, moTa) VALUES
('PGG2024001', 'A001', '2024-09-01', N'Giảm 10% cho phòng đơn A1 trong tháng 9.'),
('PGG2024001', 'A002', '2024-09-01', N'Giảm 10% cho phòng đơn A2 trong tháng 9.'),
('PGG2024001', 'B001', '2024-09-01', N'Giảm 10% cho phòng đơn B1 trong tháng 9.'),
('PGG2024001', 'B002', '2024-09-01', N'Giảm 10% cho phòng đơn B2 trong tháng 9.'),
('PGG2024001', 'C001', '2024-09-01', N'Giảm 10% cho phòng đơn C1 trong tháng 9.'),
('PGG2024002', 'A003', '2024-10-01', N'Giảm 15% cho phòng đôi A3 trong tháng 10.'),
('PGG2024002', 'A004', '2024-10-01', N'Giảm 15% cho phòng đôi A4 trong tháng 10.'),
('PGG2024002', 'B003', '2024-10-01', N'Giảm 15% cho phòng đôi B3 trong tháng 10.'),
('PGG2024002', 'B004', '2024-10-01', N'Giảm 15% cho phòng đôi B4 trong tháng 10.'),
('PGG2024003', 'A005', '2024-11-01', N'Giảm 20% cho phòng gia đình A5 trong tháng 11.'),
('PGG2024003', 'B005', '2024-11-01', N'Giảm 20% cho phòng gia đình B5 trong tháng 11.'),
('PGG2024003', 'C005', '2024-11-01', N'Giảm 20% cho phòng gia đình C5 trong tháng 11.'),
('PGG2024003', 'D005', '2024-11-01', N'Giảm 20% cho phòng gia đình D5 trong tháng 11.'),
('PGG2024004', 'A006', '2024-09-01', N'Giảm 25% cho phòng VIP A6 trong tháng 9.'),
('PGG2024004', 'B006', '2024-09-01', N'Giảm 25% cho phòng VIP B6 trong tháng 9.'),
('PGG2024004', 'C006', '2024-09-01', N'Giảm 25% cho phòng VIP C6 trong tháng 9.'),
('PGG2024005', 'D006', '2024-10-01', N'Giảm 30% cho phòng VIP D6 trong tháng 10.'),
('PGG2024005', 'E005', '2024-10-01', N'Giảm 30% cho phòng gia đình E5 trong tháng 10.'),
('PGG2024005', 'E003', '2024-10-01', N'Giảm 30% cho phòng đôi E3 trong tháng 10.');

--phieu dat phong
INSERT INTO PhieuDatPhong (maPDP, tinhTrangPDP, ngayDat, ngayDen, ngayDi, maKH, maNV, maPhong) VALUES
('PDP-001', 0, '2024-09-01 08:23:00', '2024-10-30 15:37:00', '2024-11-06 10:45:00', 'KH-001', '20-AB12', 'A001'),
('PDP-002', 0, '2024-09-02 12:04:00', '2024-10-31 09:30:00', '2024-11-07 14:22:00', 'KH-002', '20-CD34', 'A002'),
('PDP-003', 0, '2024-09-03 18:11:00', '2024-11-01 21:49:00', '2024-11-08 07:55:00', 'KH-003', '19-EF56', 'A003'),
('PDP-004', 1, '2024-09-04 05:45:00', '2024-09-08 20:03:00', '2024-09-13 13:10:00', 'KH-004', '21-GH78', 'A004'),
('PDP-005', 1, '2024-09-05 23:15:00', '2024-09-09 06:34:00', '2024-09-14 11:27:00', 'KH-005', '22-IJ90', 'A005'),
('PDP-006', 0, '2024-09-06 17:29:00', '2024-11-02 02:45:00', '2024-11-09 22:55:00', 'KH-006', '20-KL12', 'A006'),
('PDP-007', 1, '2024-09-07 01:00:00', '2024-09-11 16:30:00', '2024-09-16 04:18:00', 'KH-007', '19-MN34', 'B001'),
('PDP-008', 1, '2024-09-08 14:12:00', '2024-09-12 03:56:00', '2024-09-17 19:45:00', 'KH-008', '21-QR78', 'B002'),
('PDP-009', 2, '2024-09-09 10:20:00', '2024-09-13 11:12:00', '2024-09-18 08:27:00', 'KH-009', '18-ST90', 'B003'),
('PDP-010', 0, '2024-09-10 22:11:00', '2024-11-03 17:47:00', '2024-11-10 00:59:00', 'KH-010', '20-UV12', 'B004'),
('PDP-011', 1, '2024-09-11 04:38:00', '2024-09-15 12:30:00', '2024-09-20 21:17:00', 'KH-011', '17-OP56', 'B005'),
('PDP-012', 0, '2024-09-12 09:53:00', '2024-11-04 19:24:00', '2024-11-11 05:30:00', 'KH-012', '21-YZ56', 'B006'),
('PDP-013', 0, '2024-09-13 13:37:00', '2024-11-05 08:44:00', '2024-11-12 15:58:00', 'KH-013', '19-AB78', 'C001'),
('PDP-014', 0, '2024-09-14 20:20:00', '2024-11-06 23:00:00', '2024-11-13 09:11:00', 'KH-014', '22-CD90', 'C002'),
('PDP-015', 0, '2024-09-15 11:58:00', '2024-11-07 02:13:00', '2024-11-14 14:47:00', 'KH-015', '20-EF12', 'C003'),
('PDP-016', 0, '2024-09-16 07:29:00', '2024-11-08 10:30:00', '2024-11-15 18:55:00', 'KH-016', '19-GH34', 'C004'),
('PDP-017', 0, '2024-09-17 23:00:00', '2024-11-09 21:42:00', '2024-11-16 06:12:00', 'KH-017', '18-IJ56', 'C005'),
('PDP-018', 1, '2024-09-18 15:16:00', '2024-09-22 14:55:00', '2024-09-27 13:01:00', 'KH-018', '21-KL78', 'C006'),
('PDP-019', 2, '2024-09-19 09:00:00', '2024-09-23 04:00:00', '2024-09-28 11:45:00', 'KH-019', '17-MN90', 'D001'),
('PDP-020', 1, '2024-09-20 03:45:00', '2024-09-24 18:27:00', '2024-09-29 22:50:00', 'KH-020', '19-AB78', 'D002');



--thue VAT
INSERT INTO ThueVAT (maSoThue, mucThue, ngayApDung, moTa)
VALUES
('01012345', 5.0, '2023-01-01', N'Thuế giá trị gia tăng 5%'),
('01098765', 10.0, '2023-01-01', N'Thuế giá trị gia tăng 10%'),
('02012346', 15.0, '2023-01-01', N'Thuế giá trị gia tăng 15%'),
('02098766', 20.0, '2023-01-01', N'Thuế giá trị gia tăng 20%');

--hoa don
INSERT INTO HoaDon (maHD, ngayLapHD, ngayNhanPhong, ngayTraPhong, soPhongDat, maKH, maNV, maSoThue, maPhong)
VALUES
('28463917', '2024-09-01', '2024-08-30', '2024-09-01', 1, 'KH-001', '20-AB12', '01012345', 'A001'),
('98376245', '2024-09-02', '2024-09-01', '2024-09-02', 1, 'KH-002', '20-CD34', '01098765', 'A002'),
('27468930', '2024-09-03', '2024-09-01', '2024-09-03', 1, 'KH-003', '19-EF56', '02012346', 'A003'),
('10987654', '2024-09-04', '2024-09-02', '2024-09-04', 1, 'KH-004', '21-GH78', '02098766', 'A004'),
('65748329', '2024-09-05', '2024-09-04', '2024-09-05', 2, 'KH-005', '22-IJ90', '01012345', 'A005'),
('89012345', '2024-09-06', '2024-09-03', '2024-09-06', 1, 'KH-006', '20-AB12', '01098765', 'A006'),
('54321098', '2024-09-07', '2024-09-05', '2024-09-07', 2, 'KH-007', '20-CD34', '02012346', 'B001'),
('13579246', '2024-09-08', '2024-09-04', '2024-09-08', 1, 'KH-008', '19-EF56', '02098766', 'B002'),
('24681357', '2024-09-09', '2024-09-07', '2024-09-09', 1, 'KH-009', '21-GH78', '01012345', 'B003'),
('36925814', '2024-09-10', '2024-09-08', '2024-09-10', 1, 'KH-010', '22-IJ90', '01098765', 'B004'),
('98765432', '2024-09-11', '2024-09-06', '2024-09-11', 1, 'KH-011', '20-AB12', '02012346', 'B005'),
('45678901', '2024-09-12', '2024-09-09', '2024-09-12', 2, 'KH-012', '20-CD34', '02098766', 'B006'),
('32165498', '2024-09-13', '2024-09-10', '2024-09-13', 1, 'KH-013', '19-EF56', '01012345', 'C001'),
('65412378', '2024-09-14', '2024-09-11', '2024-09-14', 1, 'KH-014', '21-GH78', '01098765', 'C002'),
('78901234', '2024-09-15', '2024-09-14', '2024-09-15', 2, 'KH-015', '22-IJ90', '02012346', 'C003'),
('01234567', '2024-09-16', '2024-09-14', '2024-09-16', 1, 'KH-016', '20-AB12', '02098766', 'C004'),
('19876543', '2024-09-17', '2024-09-13', '2024-09-17', 1, 'KH-017', '20-CD34', '01012345', 'C005'),
('23456789', '2024-09-18', '2024-09-16', '2024-09-18', 1, 'KH-018', '19-EF56', '01098765', 'C006'),
('87654321', '2024-09-19', '2024-09-18', '2024-09-19', 1, 'KH-019', '21-GH78', '02012346', 'D001'),
('54321987', '2024-09-20', '2024-09-17', '2024-09-20', 2, 'KH-020', '22-IJ90', '02098766', 'D002');


--chi tiet hoa don
INSERT INTO ChiTietHoaDon (maHD, maPhong, maDV, thoiGianThuePhong, soLuongGiuongPhu, soLuongDV, soLuongPhong) VALUES
('28463917', 'A001', '24AB1C', '2024-09-01', 1, 1, 1),
('28463917', 'A001', '24D3F2', '2024-09-01', 0, 1, 2),
('98376245', 'A002', '24G5H4', '2024-09-02', 1, 2, 1),
('98376245', 'A002', '24J8K9', '2024-09-02', 0, 1, 1),
('27468930', 'A003', '24P4Q6', '2024-09-03', 1, 1, 3),
('10987654', 'A004', '24L2N7', '2024-09-04', 0, 2, 1),
('65748329', 'A005', '24R9T1', '2024-09-05', 1, 1, 2),
('89012345', 'A006', '24V6X8', '2024-09-06', 0, 1, 1),
('54321098', 'B001', '24T5U0', '2024-09-07', 1, 2, 1),
('13579246', 'B002', '24AB1C', '2024-09-08', 0, 1, 1),
('24681357', 'B003', '24D3F2', '2024-09-09', 1, 1, 1),
('36925814', 'B004', '24G5H4', '2024-09-10', 0, 2, 1),
('98765432', 'B005', '24J8K9', '2024-09-11', 1, 2, 3),
('45678901', 'B006', '24P4Q6', '2024-09-12', 0, 1, 1),
('32165498', 'C001', '24R9T1', '2024-09-13', 1, 2, 1),
('65412378', 'C002', '24V6X8', '2024-09-14', 0, 1, 2),
('78901234', 'C003', '24AB1C', '2024-09-15', 1, 1, 1),
('01234567', 'C004', '24D3F2', '2024-09-16', 0, 2, 1),
('19876543', 'C005', '24G5H4', '2024-09-17', 1, 1, 1),
('23456789', 'C006', '24J8K9', '2024-09-18', 0, 1, 2),
('87654321', 'D001', '24P4Q6', '2024-09-19', 1, 2, 1),
('54321987', 'D002', '24R9T1', '2024-09-20', 0, 1, 1);
