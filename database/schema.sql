/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2022 (16.0.1135)
    Source Database Engine Edition : Microsoft SQL Server Enterprise Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2022
    Target Database Engine Edition : Microsoft SQL Server Enterprise Edition
    Target Database Engine Type : Standalone SQL Server
*/
USE [master]
GO
/****** Object:  Database [finalis_jti]    Script Date: 13/12/2024 18:53:02 ******/
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'finalis_jti')
CREATE DATABASE [finalis_jti]

USE [finalis_jti]
GO
/****** Object:  Schema [BERKAS]    Script Date: 13/12/2024 18:53:03 ******/
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'BERKAS')
EXEC sys.sp_executesql N'CREATE SCHEMA [BERKAS]'
GO

/****** Object:  Schema [TEMP]    Script Date: 13/12/2024 18:53:03 ******/
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'TEMP')
EXEC sys.sp_executesql N'CREATE SCHEMA [TEMP]'
GO

/****** Object:  Schema [USERS]    Script Date: 13/12/2024 18:53:03 ******/
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'USERS')
EXEC sys.sp_executesql N'CREATE SCHEMA [USERS]'
GO

/****** Object:  Schema [VER]    Script Date: 13/12/2024 18:53:03 ******/
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'VER')
EXEC sys.sp_executesql N'CREATE SCHEMA [VER]'
GO



/****** Object:  Table [BERKAS].[Prodi]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BERKAS].[Prodi]') AND type in (N'U'))
BEGIN
CREATE TABLE [BERKAS].[Prodi](
	[id_berkas_prodi] [varchar](10) NOT NULL,
	[nim] [varchar](12) NOT NULL,
	[tanggal_request] [date] NULL,
	[toeic] [varchar](max) NOT NULL,
	[distribusi_skripsi] [varchar](max) NOT NULL,
	[distribusi_magang] [varchar](max) NOT NULL,
	[surat_bebas_kompen] [varchar](max) NOT NULL,
 CONSTRAINT [PK__Prodi__4758722CF8C373E2] PRIMARY KEY CLUSTERED
(
	[id_berkas_prodi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO



/****** Object:  Table [BERKAS].[TA]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BERKAS].[TA]') AND type in (N'U'))
BEGIN
CREATE TABLE [BERKAS].[TA](
	[id_ta] [varchar](10) NOT NULL,
	[nim] [varchar](12) NOT NULL,
	[tanggal_request] [date] NULL,
	[laporan_TA] [varchar](max) NOT NULL,
	[aplikasi] [varchar](max) NOT NULL,
	[bukti_publikasi] [varchar](max) NOT NULL,
 CONSTRAINT [PK__TA__014840F23C007E6C] PRIMARY KEY CLUSTERED
(
	[id_ta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO



/****** Object:  Table [BERKAS].[Tanggungan]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]') AND type in (N'U'))
BEGIN
CREATE TABLE [BERKAS].[Tanggungan](
	[id_tanggungan] [int] IDENTITY(1,1) NOT NULL,
	[nim] [varchar](12) NOT NULL,
	[jenis_tanggungan] [varchar](20) NOT NULL,
	[status_tanggungan] [varchar](20) NOT NULL,
 CONSTRAINT [PK__Tanggung__3F3C9A2AECA35B8C] PRIMARY KEY CLUSTERED
(
	[id_tanggungan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO



/****** Object:  Table [TEMP].[Surat]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TEMP].[Surat]') AND type in (N'U'))
BEGIN
CREATE TABLE [TEMP].[Surat](
	[id_surat] [int] IDENTITY(1,1) NOT NULL,
	[nama_surat] [varchar](100) NOT NULL,
	[keperluan_surat] [varchar](100) NOT NULL,
	[file_surat] [varchar](max) NOT NULL,
 CONSTRAINT [PK__Surat__0DE732E1A86680EE] PRIMARY KEY CLUSTERED
(
	[id_surat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO



/****** Object:  Table [USERS].[Admin]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[USERS].[Admin]') AND type in (N'U'))
BEGIN
CREATE TABLE [USERS].[Admin](
	[id_admin] [varchar](12) NOT NULL,
	[nama_lengkap] [varchar](50) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[jabatan] [varchar](20) NOT NULL,
	[foto_profil] [varchar](max) NOT NULL,
 CONSTRAINT [PK__Admin__89472E956246103E] PRIMARY KEY CLUSTERED
(
	[id_admin] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO



/****** Object:  Table [USERS].[Mahasiswa]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[USERS].[Mahasiswa]') AND type in (N'U'))
BEGIN
CREATE TABLE [USERS].[Mahasiswa](
	[nim] [varchar](12) NOT NULL,
	[nama_lengkap] [varchar](50) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[jurusan] [varchar](50) NOT NULL,
	[prodi] [varchar](30) NOT NULL,
	[tahun_masuk] [varchar](10) NOT NULL,
	[foto_profil] [varchar](max) NOT NULL,
 CONSTRAINT [PK__Mahasisw__DF97D0EA0FB2FD0A] PRIMARY KEY CLUSTERED
(
	[nim] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO



/****** Object:  Table [VER].[LogAktivitas]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[LogAktivitas]') AND type in (N'U'))
BEGIN
CREATE TABLE [VER].[LogAktivitas](
	[id_log] [int] IDENTITY(1,1) NOT NULL,
	[id_admin] [varchar](12) NOT NULL,
	[id_berkas] [varchar](20) NOT NULL,
	[status_sebelumnya] [varchar](20) NULL,
	[status_sesudahnya] [varchar](50) NOT NULL,
	[waktu_aktivitas] [datetime] NOT NULL,
 CONSTRAINT [PK__LogAktiv__6CC851FE5EA77BD4] PRIMARY KEY CLUSTERED
(
	[id_log] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO



/****** Object:  Table [VER].[Notifikasi]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[Notifikasi]') AND type in (N'U'))
BEGIN
CREATE TABLE [VER].[Notifikasi](
	[id_notifikasi] [int] IDENTITY(1,1) NOT NULL,
	[pesan] [varchar](255) NOT NULL,
	[id_admin] [varchar](12) NOT NULL,
	[nim] [varchar](12) NOT NULL,
	[jenis_notifikasi] [varchar](20) NOT NULL,
	[status_notifikasi] [varchar](20) NOT NULL,
	[tujuan_notifikasi] [varchar](20) NOT NULL,
 CONSTRAINT [PK__Notifika__8FD1662A26ADA92A] PRIMARY KEY CLUSTERED
(
	[id_notifikasi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO



/****** Object:  Table [VER].[VerifikasiBerkas]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]') AND type in (N'U'))
BEGIN
CREATE TABLE [VER].[VerifikasiBerkas](
	[id_verifikasi] [int] IDENTITY(1,1) NOT NULL,
	[id_berkas] [varchar](10) NOT NULL,
	[jenis_berkas] [varchar](20) NOT NULL,
	[id_admin] [varchar](12) NOT NULL,
	[status_verifikasi] [varchar](50) NOT NULL,
	[tanggal_verifikasi] [date] NULL,
	[keterangan_verifikasi] [varchar](max) NULL,
 CONSTRAINT [PK__Verifika__98242D436B0692E1] PRIMARY KEY CLUSTERED
(
	[id_verifikasi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BERKAS].[DF__Prodi__tanggal_r__46E78A0C]') AND type = 'D')
BEGIN
ALTER TABLE [BERKAS].[Prodi] ADD  CONSTRAINT [DF__Prodi__tanggal_r__46E78A0C]  DEFAULT (getdate()) FOR [tanggal_request]
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BERKAS].[DF__TA__tanggal_requ__47DBAE45]') AND type = 'D')
BEGIN
ALTER TABLE [BERKAS].[TA] ADD  CONSTRAINT [DF__TA__tanggal_requ__47DBAE45]  DEFAULT (getdate()) FOR [tanggal_request]
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[DF__LogAktivi__statu__48CFD27E]') AND type = 'D')
BEGIN
ALTER TABLE [VER].[LogAktivitas] ADD  CONSTRAINT [DF__LogAktivi__statu__48CFD27E]  DEFAULT ('Diajukan') FOR [status_sebelumnya]
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[DF__LogAktivi__waktu__49C3F6B7]') AND type = 'D')
BEGIN
ALTER TABLE [VER].[LogAktivitas] ADD  CONSTRAINT [DF__LogAktivi__waktu__49C3F6B7]  DEFAULT (getdate()) FOR [waktu_aktivitas]
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[VER].[DF__Verifikas__tangg__4AB81AF0]') AND type = 'D')
BEGIN
ALTER TABLE [VER].[VerifikasiBerkas] ADD  CONSTRAINT [DF__Verifikas__tangg__4AB81AF0]  DEFAULT (getdate()) FOR [tanggal_verifikasi]
END
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__Prodi__nim__4BAC3F29]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Prodi]'))
ALTER TABLE [BERKAS].[Prodi]  WITH CHECK ADD  CONSTRAINT [FK__Prodi__nim__4BAC3F29] FOREIGN KEY([nim])
REFERENCES [USERS].[Mahasiswa] ([nim])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__Prodi__nim__4BAC3F29]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Prodi]'))
ALTER TABLE [BERKAS].[Prodi] CHECK CONSTRAINT [FK__Prodi__nim__4BAC3F29]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__TA__nim__4CA06362]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[TA]'))
ALTER TABLE [BERKAS].[TA]  WITH CHECK ADD  CONSTRAINT [FK__TA__nim__4CA06362] FOREIGN KEY([nim])
REFERENCES [USERS].[Mahasiswa] ([nim])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__TA__nim__4CA06362]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[TA]'))
ALTER TABLE [BERKAS].[TA] CHECK CONSTRAINT [FK__TA__nim__4CA06362]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__Tanggungan__nim__4D94879B]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan]  WITH CHECK ADD  CONSTRAINT [FK__Tanggungan__nim__4D94879B] FOREIGN KEY([nim])
REFERENCES [USERS].[Mahasiswa] ([nim])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[BERKAS].[FK__Tanggungan__nim__4D94879B]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan] CHECK CONSTRAINT [FK__Tanggungan__nim__4D94879B]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__LogAktivi__id_ad__4E88ABD4]') AND parent_object_id = OBJECT_ID(N'[VER].[LogAktivitas]'))
ALTER TABLE [VER].[LogAktivitas]  WITH CHECK ADD  CONSTRAINT [FK__LogAktivi__id_ad__4E88ABD4] FOREIGN KEY([id_admin])
REFERENCES [USERS].[Admin] ([id_admin])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__LogAktivi__id_ad__4E88ABD4]') AND parent_object_id = OBJECT_ID(N'[VER].[LogAktivitas]'))
ALTER TABLE [VER].[LogAktivitas] CHECK CONSTRAINT [FK__LogAktivi__id_ad__4E88ABD4]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Notifikas__id_ad__4F7CD00D]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi]  WITH CHECK ADD  CONSTRAINT [FK__Notifikas__id_ad__4F7CD00D] FOREIGN KEY([id_admin])
REFERENCES [USERS].[Admin] ([id_admin])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Notifikas__id_ad__4F7CD00D]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi] CHECK CONSTRAINT [FK__Notifikas__id_ad__4F7CD00D]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Notifikasi__nim__5070F446]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi]  WITH CHECK ADD  CONSTRAINT [FK__Notifikasi__nim__5070F446] FOREIGN KEY([nim])
REFERENCES [USERS].[Mahasiswa] ([nim])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Notifikasi__nim__5070F446]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi] CHECK CONSTRAINT [FK__Notifikasi__nim__5070F446]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Verifikas__id_ad__5165187F]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas]  WITH CHECK ADD  CONSTRAINT [FK__Verifikas__id_ad__5165187F] FOREIGN KEY([id_admin])
REFERENCES [USERS].[Admin] ([id_admin])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[VER].[FK__Verifikas__id_ad__5165187F]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas] CHECK CONSTRAINT [FK__Verifikas__id_ad__5165187F]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[BERKAS].[CK__Tanggunga__jenis__52593CB8]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan]  WITH CHECK ADD  CONSTRAINT [CK__Tanggunga__jenis__52593CB8] CHECK  (([jenis_tanggungan]='Tanggungan Prodi' OR [jenis_tanggungan]='Tanggungan TA'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[BERKAS].[CK__Tanggunga__jenis__52593CB8]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan] CHECK CONSTRAINT [CK__Tanggunga__jenis__52593CB8]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[BERKAS].[CK__Tanggunga__statu__534D60F1]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan]  WITH CHECK ADD  CONSTRAINT [CK__Tanggunga__statu__534D60F1] CHECK  (([status_tanggungan]='Berkas Ditolak' OR [status_tanggungan]='Belum Selesai' OR [status_tanggungan]='Selesai'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[BERKAS].[CK__Tanggunga__statu__534D60F1]') AND parent_object_id = OBJECT_ID(N'[BERKAS].[Tanggungan]'))
ALTER TABLE [BERKAS].[Tanggungan] CHECK CONSTRAINT [CK__Tanggunga__statu__534D60F1]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Admin__jabatan__5441852A]') AND parent_object_id = OBJECT_ID(N'[USERS].[Admin]'))
ALTER TABLE [USERS].[Admin]  WITH CHECK ADD  CONSTRAINT [CK__Admin__jabatan__5441852A] CHECK  (([jabatan]='Admin Jurusan' OR [jabatan]='Admin TA' OR [jabatan]='Admin Prodi'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Admin__jabatan__5441852A]') AND parent_object_id = OBJECT_ID(N'[USERS].[Admin]'))
ALTER TABLE [USERS].[Admin] CHECK CONSTRAINT [CK__Admin__jabatan__5441852A]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Mahasiswa__jurus__5535A963]') AND parent_object_id = OBJECT_ID(N'[USERS].[Mahasiswa]'))
ALTER TABLE [USERS].[Mahasiswa]  WITH CHECK ADD  CONSTRAINT [CK__Mahasiswa__jurus__5535A963] CHECK  (([jurusan]='Teknik Kimia' OR [jurusan]='Teknik Mesin' OR [jurusan]='Teknik Elektro' OR [jurusan]='Akuntansi' OR [jurusan]='Administrasi Niaga' OR [jurusan]='Teknologi Informasi'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Mahasiswa__jurus__5535A963]') AND parent_object_id = OBJECT_ID(N'[USERS].[Mahasiswa]'))
ALTER TABLE [USERS].[Mahasiswa] CHECK CONSTRAINT [CK__Mahasiswa__jurus__5535A963]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Mahasiswa__prodi__5629CD9C]') AND parent_object_id = OBJECT_ID(N'[USERS].[Mahasiswa]'))
ALTER TABLE [USERS].[Mahasiswa]  WITH CHECK ADD  CONSTRAINT [CK__Mahasiswa__prodi__5629CD9C] CHECK  (([prodi]='D2 Pengembangan Piranti Lunak Situs' OR [prodi]='D4 Sistem Informasi Bisnis' OR [prodi]='D4 Teknik Informatika'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[USERS].[CK__Mahasiswa__prodi__5629CD9C]') AND parent_object_id = OBJECT_ID(N'[USERS].[Mahasiswa]'))
ALTER TABLE [USERS].[Mahasiswa] CHECK CONSTRAINT [CK__Mahasiswa__prodi__5629CD9C]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__jenis__571DF1D5]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi]  WITH CHECK ADD  CONSTRAINT [CK__Notifikas__jenis__571DF1D5] CHECK  (([jenis_notifikasi]='Tanggungan Prodi' OR [jenis_notifikasi]='Tanggungan TA'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__jenis__571DF1D5]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi] CHECK CONSTRAINT [CK__Notifikas__jenis__571DF1D5]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__statu__5812160E]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi]  WITH CHECK ADD  CONSTRAINT [CK__Notifikas__statu__5812160E] CHECK  (([status_notifikasi]='Belum Dibaca' OR [status_notifikasi]='Dibaca'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__statu__5812160E]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi] CHECK CONSTRAINT [CK__Notifikas__statu__5812160E]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__tujua__59063A47]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi]  WITH CHECK ADD  CONSTRAINT [CK__Notifikas__tujua__59063A47] CHECK  (([tujuan_notifikasi]='Admin' OR [tujuan_notifikasi]='Mahasiswa'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Notifikas__tujua__59063A47]') AND parent_object_id = OBJECT_ID(N'[VER].[Notifikasi]'))
ALTER TABLE [VER].[Notifikasi] CHECK CONSTRAINT [CK__Notifikas__tujua__59063A47]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Verifikas__jenis__59FA5E80]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas]  WITH CHECK ADD  CONSTRAINT [CK__Verifikas__jenis__59FA5E80] CHECK  (([jenis_berkas]='Prodi' OR [jenis_berkas]='TA'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Verifikas__jenis__59FA5E80]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas] CHECK CONSTRAINT [CK__Verifikas__jenis__59FA5E80]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Verifikas__statu__5AEE82B9]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas]  WITH CHECK ADD  CONSTRAINT [CK__Verifikas__statu__5AEE82B9] CHECK  (([status_verifikasi]='Ditolak' OR [status_verifikasi]='Disetujui' OR [status_verifikasi]='Diajukan'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[VER].[CK__Verifikas__statu__5AEE82B9]') AND parent_object_id = OBJECT_ID(N'[VER].[VerifikasiBerkas]'))
ALTER TABLE [VER].[VerifikasiBerkas] CHECK CONSTRAINT [CK__Verifikas__statu__5AEE82B9]
GO


/****** Object:  StoredProcedure [dbo].[sp_InsertBerkasProdi]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_InsertBerkasProdi]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[sp_InsertBerkasProdi] AS'
END
GO
-- Store Procedure Untuk INSERT BERKAS PRODI, BERKAS.Tanggungan, VER.VerifikasiBerkas
ALTER PROCEDURE [dbo].[sp_InsertBerkasProdi]
    @nim VARCHAR(12),
    @toeic VARCHAR(MAX),
	@distribusi_skripsi VARCHAR(MAX),
	@distribusi_magang VARCHAR(MAX),
	@surat_bebas_kompen VARCHAR(MAX),
	@id_admin VARCHAR(12)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @last_id VARCHAR(10);
    DECLARE @new_id VARCHAR(10);
    DECLARE @next_number INT;

    -- Ambil ID terakhir
    SELECT @last_id = MAX(id_berkas_prodi) FROM BERKAS.Prodi;


    SET @next_number = ISNULL(CAST(SUBSTRING(@last_id, 3, LEN(@last_id) - 2) AS INT), 0) + 1;

    SET @new_id = 'PR' + RIGHT('0000' + CAST(@next_number AS VARCHAR), 4);

    INSERT INTO BERKAS.Prodi (id_berkas_prodi, nim, toeic, distribusi_skripsi, distribusi_magang, surat_bebas_kompen)
    VALUES (@new_id, @nim, @toeic, @distribusi_skripsi, @distribusi_magang, @surat_bebas_kompen);

	INSERT INTO BERKAS.Tanggungan (nim, jenis_tanggungan, status_tanggungan)
	VALUES (@nim, 'Tanggungan Prodi', 'Belum Selesai')

    INSERT INTO VER.VerifikasiBerkas (id_berkas, jenis_berkas, id_admin, status_verifikasi, tanggal_verifikasi, keterangan_verifikasi)
    VALUES (@new_id, 'Prodi', @id_admin, 'Diajukan', NULL , 'Berkas Prodi telah diajukan untuk verifikasi.');

	--Memasukkan ke Tabel Notifikasi Admin Prodi
	INSERT INTO VER.Notifikasi (pesan, id_admin, nim, jenis_notifikasi, status_notifikasi, tujuan_notifikasi)
	VALUES ('Pengajuan Berkas Prodi dilakukan oleh nim ' + @nim + '. Mohon segera verifikasi dalam waktu 2x24 jam.', @id_admin, @nim, 'Tanggungan Prodi', 'Belum Dibaca', 'Admin');
END;
GO



/****** Object:  StoredProcedure [dbo].[sp_InsertBerkasTA]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_InsertBerkasTA]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[sp_InsertBerkasTA] AS'
END
GO
ALTER PROCEDURE [dbo].[sp_InsertBerkasTA]
    @nim VARCHAR(30),
    @laporan_TA VARCHAR(MAX),
    @aplikasi VARCHAR(MAX),
    @bukti_publikasi VARCHAR(MAX),
	@id_admin VARCHAR(12)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @last_id VARCHAR(10);
    DECLARE @new_id VARCHAR(10);
    DECLARE @next_number INT;

    -- Ambil ID terakhir
    SELECT @last_id = MAX(id_ta) FROM BERKAS.TA;

    -- Angka dari ID terakhir dan tambahkan 1
    SET @next_number = ISNULL(CAST(SUBSTRING(@last_id, 3, LEN(@last_id) - 2) AS INT), 0) + 1;

    -- Buat ID baru dengan format "TA" + angka yang di-increment
    SET @new_id = 'TA' + RIGHT('0000' + CAST(@next_number AS VARCHAR), 4);

    -- Masukkan data baru ke tabel
    INSERT INTO BERKAS.TA (id_ta, nim, laporan_TA, aplikasi, bukti_publikasi)
    VALUES (@new_id, @nim, @laporan_TA, @aplikasi, @bukti_publikasi);

	--Masukkan data tanggungan
	INSERT INTO BERKAS.Tanggungan (nim, jenis_tanggungan, status_tanggungan)
	VALUES (@nim, 'Tanggungan TA', 'Belum Selesai')

	-- Menyisipkan data ke tabel VerifikasiBerkas
    INSERT INTO VER.VerifikasiBerkas (id_berkas, jenis_berkas, id_admin, status_verifikasi, tanggal_verifikasi, keterangan_verifikasi)
    VALUES (@new_id, 'TA', @id_admin, 'Diajukan', NULL , 'Berkas TA telah diajukan untuk verifikasi.');

	--Memasukkan ke Tabel Notifikasi Admin TA
	INSERT INTO VER.Notifikasi (pesan, id_admin, nim, jenis_notifikasi, status_notifikasi, tujuan_notifikasi)
	VALUES ('Pengajuan Berkas TA dilakukan oleh nim ' + @nim + '. Mohon segera verifikasi dalam waktu 2x24 jam.', @id_admin, @nim, 'Tanggungan TA', 'Belum Dibaca', 'Admin');
END;
GO



/****** Object:  StoredProcedure [dbo].[sp_UpdateVerifikasiBerkasProdi]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_UpdateVerifikasiBerkasProdi]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[sp_UpdateVerifikasiBerkasProdi] AS'
END
GO
ALTER PROCEDURE [dbo].[sp_UpdateVerifikasiBerkasProdi]
    @id_berkas VARCHAR(10),
    @status_verifikasi VARCHAR(50),
    @keterangan_verifikasi VARCHAR(MAX),
    @id_admin VARCHAR(12)
AS
BEGIN
	DECLARE @nim VARCHAR(12)
	DECLARE @status_tanggungan VARCHAR(30)

	--Menngambil nim
	SELECT @nim = nim
	FROM BERKAS.Prodi
	WHERE id_berkas_prodi = @id_berkas;
    -- Update status verifikasi
    UPDATE VER.VerifikasiBerkas
    SET
        status_verifikasi = @status_verifikasi,
        tanggal_verifikasi = GETDATE(),
        keterangan_verifikasi = @keterangan_verifikasi
    WHERE id_berkas = @id_berkas AND jenis_berkas = 'Prodi';

    -- Menyimpan aktivitas ke dalam log
    INSERT INTO VER.LogAktivitas (id_admin, id_berkas, status_sesudahnya, waktu_aktivitas)
    VALUES
        (@id_admin, @id_berkas, @status_verifikasi, GETDATE());

	--Notifikasi Mahasiswa
	INSERT INTO VER.Notifikasi (pesan, id_admin, nim, jenis_notifikasi, status_notifikasi, tujuan_notifikasi)
	VALUES
	(CASE
		WHEN @status_verifikasi = 'Disetujui' THEN 'Selamat, berkas pengajuan prodi Anda telah disetujui oleh Admin Prodi!'
		WHEN @status_verifikasi = 'Ditolak' THEN 'Mohon maaf, berkas pengajuan prodi Anda ditolak oleh Admin Prodi. Mohon kirimkan ulang berkas sesuai dengan keterangan pengembalian berkas.'
		END,
		@id_admin, @nim, 'Tanggungan Prodi', 'Belum Dibaca', 'Mahasiswa')

	--Update Status Tanggungan
	UPDATE BERKAS.Tanggungan
	SET
		status_tanggungan =
		(CASE
			WHEN @status_verifikasi = 'Disetujui' THEN 'Selesai'
			WHEN @status_verifikasi = 'Ditolak' THEN 'Berkas Ditolak'
			END
		)
	WHERE nim = @nim AND jenis_tanggungan = 'Tanggungan Prodi';
END;
GO



/****** Object:  StoredProcedure [dbo].[sp_UpdateVerifikasiBerkasTA]    Script Date: 13/12/2024 18:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_UpdateVerifikasiBerkasTA]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[sp_UpdateVerifikasiBerkasTA] AS'
END
GO
ALTER PROCEDURE [dbo].[sp_UpdateVerifikasiBerkasTA]
    @id_berkas VARCHAR(10),
    @status_verifikasi VARCHAR(50),
    @keterangan_verifikasi VARCHAR(MAX),
    @id_admin VARCHAR(12)

AS
BEGIN
	DECLARE @nim VARCHAR(12)
	DECLARE @status_tanggungan VARCHAR(30)

	--Menngambil nim
	SELECT @nim = nim
	FROM BERKAS.TA
	WHERE id_ta = @id_berkas;

    -- Update status verifikasi
    UPDATE VER.VerifikasiBerkas
    SET
        status_verifikasi = @status_verifikasi,
        tanggal_verifikasi = GETDATE(),
        keterangan_verifikasi = @keterangan_verifikasi
    WHERE id_berkas = @id_berkas AND jenis_berkas = 'TA'

    -- Menyimpan aktivitas ke dalam log
    INSERT INTO VER.LogAktivitas (id_admin, id_berkas, status_sesudahnya, waktu_aktivitas)
    VALUES
        (@id_admin, @id_berkas, @status_verifikasi, GETDATE());


	--Notifikasi Mahasiswa
	INSERT INTO VER.Notifikasi (pesan, id_admin, nim, jenis_notifikasi, status_notifikasi, tujuan_notifikasi)
	VALUES
	(CASE
		WHEN @status_verifikasi = 'Disetujui' THEN 'Selamat, berkas pengajuan TA Anda telah disetujui oleh Admin TA!'
		WHEN @status_verifikasi = 'Ditolak' THEN 'Mohon maaf, berkas pengajuan TA Anda ditolak oleh Admin TA. Mohon kirimkan ulang berkas sesuai dengan keterangan pengembalian berkas.'
		END,
		@id_admin, @nim, 'Tanggungan TA', 'Belum Dibaca', 'Mahasiswa')

	--Update Status Tanggungan
	UPDATE BERKAS.Tanggungan
	SET
		status_tanggungan =
		(CASE
			WHEN @status_verifikasi = 'Disetujui' THEN 'Selesai'
			WHEN @status_verifikasi = 'Ditolak' THEN 'Berkas Ditolak'
			END
		)
	WHERE nim = @nim AND jenis_tanggungan = 'Tanggungan TA';
END;
GO


USE [master]
GO

ALTER DATABASE [finalis_jti] SET  READ_WRITE
GO