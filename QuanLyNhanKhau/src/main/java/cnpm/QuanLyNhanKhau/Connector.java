package cnpm.QuanLyNhanKhau;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelTamTru;
import cnpm.QuanLyNhanKhau.model.ModelTamVang;
import cnpm.QuanLyNhanKhau.model.ModelUser;

public class Connector {

//	private static String url = "jdbc:mysql://localhost:3306/quanlynhankhau";
//	private static String user = "root";
//	private static String password = "Minh_0112";
	
	private static String url = "jdbc:mysql://localhost:3306/quanlynhankhau";
	private static String user = "root";
	private static String password = "namanh202";

	private static ModelUser currentUser;

	private static Connection connection;

	public Connector() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean signIn(String taiKhoan, String matKhau) {
		currentUser = getUser(taiKhoan, matKhau);
		if (currentUser == null)
			return false;
		lsSignIn();
		return true;
	}

	public static boolean signUp(String taiKhoan, String matKhau, String tenNguoiDung) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.user (`taiKhoan`, `matKhau`, `tenNguoiDung`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, taiKhoan);
			ps.setString(2, matKhau);
			ps.setString(3, tenNguoiDung);

			ps.executeUpdate();
			ModelUser modelUser = getUser(taiKhoan, matKhau);
			lsSignUp(modelUser.getIdUser());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
//	public static boolean addNhanKhau(ModelNhanKhau nhanKhau) {
//		Class<? extends ModelNhanKhau> nhanKhauClass = nhanKhau.getClass();
//		StringBuilder builder = new StringBuilder("INSERT INTO quanlynhankhau.nhankhau (");
//		for (Field field : nhanKhauClass.getDeclaredFields()) {
//			builder.append("`").append(field.getName()).append("`").append(", ");
//		}
//	}

	public static boolean addNhanKhau(String hoTen, String biDanh, LocalDate ngaySinh, String gioiTinh, String cccd,
									LocalDate ngayCap, String noiCap, String nguyenQuan, String danToc,
									String noiThuongTru, LocalDate ngayDangKyThuongTru,String trinhDoHocVan,
									String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec, String tonGiao,
									String quocTich, String trinhDoChuyenMon,String ghiChu) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.nhankhau (`hoTen`, `biDanh`, `ngaySinh`, `gioiTinh`, `cccd`,"
				+ "`ngayCap`, `noiCap`, `nguyenQuan`, `danToc`, `noiThuongTru`, `ngayDangKyThuongTru`, `trinhDoHocVan`,"
				+ " `trinhDoNgoaiNgu`, `ngheNghiep`, `noiLamViec`, `tonGiao`, `quocTich`, `trinhDoChuyenMon`, `ghiChu`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hoTen);
			ps.setString(2, biDanh);
			ps.setDate(3, Date.valueOf(ngaySinh));
			ps.setString(4, gioiTinh);
			ps.setString(5, cccd);
			ps.setDate(6, ngayCap != null ? Date.valueOf(ngayCap) : null);
			ps.setString(7, noiCap);
			ps.setString(8, nguyenQuan);
			ps.setString(9, danToc);
			ps.setString(10, noiThuongTru);
			ps.setDate(11, Date.valueOf(ngayDangKyThuongTru));
			ps.setString(12, trinhDoHocVan);
			ps.setString(13, trinhDoNgoaiNgu);
			ps.setString(14, ngheNghiep);
			ps.setString(15, noiLamViec);
			ps.setString(16, tonGiao);
			ps.setString(17, quocTich);
			ps.setString(18, trinhDoChuyenMon);
			ps.setString(19, ghiChu);

			ps.executeUpdate();
			ModelNhanKhau modelNhanKhau = queryNhanKhauByCCCD(cccd);
			lsAddNhanKhau(modelNhanKhau.getIdNhanKhau());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}

	public static ModelNhanKhau getNhanKhau(int idNhanKhau) {
		ModelNhanKhau modelNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.nhankhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
				lsGetNhanKhau(idNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
	}
	
	public static ModelNhanKhau queryNhanKhauByIdNhanKhau(int idNhanKhau) {
		ModelNhanKhau modelNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.nhankhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
	}
	
	public static ModelNhanKhau queryNhanKhauByCCCD(String cccd) {
		ModelNhanKhau modelNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.nhankhau\n" + "WHERE cccd = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, cccd);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
	}

	public static boolean editNhanKhau(ModelNhanKhau data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.nhankhau SET hoTen = ?, biDanh = ?, ngaySinh = ?, gioiTinh = ?, cccd = ?,"
				+ "ngayCap = ?, noiCap = ?, nguyenQuan = ?, danToc = ?, noiThuongTru = ?, ngayDangKyThuongTru = ?, "
				+ "trinhDoHocVan = ?, trinhDoNgoaiNgu = ?, ngheNghiep = ?, noiLamViec = ?, "
				+ "tonGiao = ?, quocTich = ?, trinhDoChuyenMon = ?, ghiChu = ?\n"
				+ "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, data.getHoTen());
			ps.setString(2, data.getBiDanh());
			ps.setDate(3, data.getNgaySinh());
			ps.setString(4, data.getGioiTinh());
			ps.setString(5, data.getCccd());
			ps.setDate(6, data.getNgayCap());
			ps.setString(7, data.getNoiCap());
			ps.setString(8, data.getNguyenQuan());
			ps.setString(9, data.getDanToc());
			ps.setString(10, data.getNoiThuongTru());
			ps.setDate(11, data.getNgayDangKyThuongTru());
			ps.setString(12, data.getTrinhDoHocVan());
			ps.setString(13, data.getTrinhDoNgoaiNgu());
			ps.setString(14, data.getNgheNghiep());
			ps.setString(15, data.getNoiLamViec());
			ps.setString(16, data.getTonGiao());
			ps.setString(17, data.getQuocTich());
			ps.setString(18, data.getTrinhDoChuyenMon());
			ps.setString(19, data.getGhiChu());
			
			ps.setInt(20, data.getIdNhanKhau());

			ps.executeUpdate();
			lsEditNhanKhau(data.getIdNhanKhau());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}

	public static boolean deleteNhanKhau(ModelNhanKhau data) {
		boolean done = false;

		deleteLsNhanKhau(data.getIdNhanKhau());
		deleteLsTamTru(data.getIdNhanKhau());
		deleteLsTamVang(data.getIdNhanKhau());

		String query = "DELETE FROM quanlynhankhau.nhankhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());

			ps.executeUpdate();
			lsDeleteNhanKhau();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}

	public static List<ModelNhanKhau> getAllNhanKhau() {
		List<ModelNhanKhau> listNhanKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.nhankhau";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelNhanKhau modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
				listNhanKhau.add(modelNhanKhau);
			}
//			lsGetAllNhanKhau();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNhanKhau;
	}

	public static List<ModelNhanKhau> searchNhanKhauByHoTen(String hoTen) {
		List<ModelNhanKhau> listNhanKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.nhankhau\n" + "WHERE hoTen like ?\n" + "ORDER BY hoTen ASC";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + hoTen + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelNhanKhau modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
				listNhanKhau.add(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNhanKhau;
	}
	
	public static List<ModelNhanKhau> searchNhanKhauByIdHoKhau(int idHoKhau) {
		List<ModelNhanKhau> listNhanKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.nhankhau as NK, quanlynhankhau.hokhaunhankhau as HKNK\n"
				+ "WHERE NK.idNhanKhau = HKNK.idNhanKhau AND HKNK.idHoKhau = ?\n"
				+ "ORDER BY hoTen ASC";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelNhanKhau modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"));
				ModelHoKhauNhanKhau modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				modelNhanKhau.setModelHoKhauNhanKhau(modelHoKhauNhanKhau);
				listNhanKhau.add(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNhanKhau;
	}

	public static boolean addHoKhau(int idNhanKhau, String diaChi, String soNha, String duongPho, String phuong,
								String quan) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.hokhau (`idNhanKhau`, `diaChi`, `soNha`, `duongPho`, `phuong`,"
				+ "`quan`)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, diaChi);
			ps.setString(3, soNha);
			ps.setString(4, duongPho);
			ps.setString(5, phuong);
			ps.setString(6, quan);

			ps.executeUpdate();
			ModelHoKhau modelHoKhau = getHoKhau(idNhanKhau, diaChi);
			lsAddHoKhau(modelHoKhau.getIdHoKhau());
			IsChangeNoiThuongTruNhanKhau(idNhanKhau, diaChi);
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelHoKhau getHoKhau(int idHoKhau) {
		ModelHoKhau modelHoKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhau\n" + "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"),
						rs.getString("soNha"), rs.getString("duongPho"), rs.getString("phuong"), rs.getString("quan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
				lsGetHoKhau(idHoKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhau;
	}
	
	public static ModelHoKhau queryHoKhauByIdHoKhau(int idHoKhau) {
		ModelHoKhau modelHoKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhau\n" + "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"),
						rs.getString("soNha"), rs.getString("duongPho"), rs.getString("phuong"), rs.getString("quan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhau;
	}
	
	public static boolean editHoKhau(ModelHoKhau data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.hokhau SET idNhanKhau = ?, diaChi = ?, soNha = ?, duongPho = ?,"
				+ "phuong = ?, quan = ?\n"
				+ "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());
			ps.setString(2, data.getDiaChi());
			ps.setString(3, data.getSoNha());
			ps.setString(4, data.getDuongPho());
			ps.setString(5, data.getPhuong());
			ps.setString(6, data.getQuan());
			ps.setInt(7, data.getIdHoKhau());

			ps.executeUpdate();
			lsEditHoKhau(data.getIdHoKhau());
			IsChangeNoiThuongTruNhanKhau(data.getIdNhanKhau(),data.getDiaChi());
			List<ModelHoKhauNhanKhau> modelHoKhauNhanKhau = searchHoKhauNhanKhauByIdHoKhau(data.getIdHoKhau());
			if (modelHoKhauNhanKhau != null) {
				modelHoKhauNhanKhau.forEach(hoKhauNhanKhau -> {
					IsChangeNoiThuongTruNhanKhau(hoKhauNhanKhau.getIdNhanKhau(), data.getDiaChi());
				});
			}
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static boolean deleteHoKhau(ModelHoKhau data) {
		boolean done = false;

		deleteLsHoKhau(data.getIdHoKhau());

		String query = "DELETE FROM quanlynhankhau.hokhau\n" + "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdHoKhau());

			ps.executeUpdate();
			lsDeleteHoKhau();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelHoKhau> getAllHoKhau(){
		List<ModelHoKhau> listHoKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hokhau";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoKhau modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"),
						rs.getString("diaChi"), rs.getString("soNha"), rs.getString("duongPho"),
						rs.getString("phuong"), rs.getString("quan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
				listHoKhau.add(modelHoKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoKhau;
	}
	
	public static ModelHoKhau searchHoKhauByIdNhanKhau(int idNhanKhau) {
		ModelHoKhau modelHoKhau = null;
		String query = "SELECT * FROM quanlynhankhau.hokhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"),
						rs.getString("diaChi"), rs.getString("soNha"), rs.getString("duongPho"),
						rs.getString("phuong"), rs.getString("quan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhau;
	}
	
	public static boolean addHoKhauNhanKhau(int idHoKhau, int idNhanKhau, String quanHe) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.hokhaunhankhau (`idHoKhau`, `idNhanKhau`, `quanHe`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);
			ps.setInt(2, idNhanKhau);
			ps.setString(3, quanHe);

			ps.executeUpdate();
			ModelHoKhauNhanKhau modelHoKhauNhanKhau = getHoKhauNhanKhau(idHoKhau, idNhanKhau);
			lsAddHoKhauNhanKhau(modelHoKhauNhanKhau.getIdHoKhauNhanKhau());
			ModelHoKhau modelHoKhau = getHoKhau(idHoKhau);
			IsChangeNoiThuongTruNhanKhau(idNhanKhau, modelHoKhau.getDiaChi());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static boolean addChuHoToHoKhauNhanKhau(int idHoKhau, int idNhanKhau) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.hokhaunhankhau (`idHoKhau`, `idNhanKhau`, `quanHe`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);
			ps.setInt(2, idNhanKhau);
			ps.setString(3, "Chủ Hộ");
			
			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelHoKhauNhanKhau getHoKhauNhanKhau(int idHoKhauNhanKhau) {
		ModelHoKhauNhanKhau modelHoKhauNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idHoKhauNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhauNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhauNhanKhau.setModelNhanKhau(modelNhanKhau);
				lsGetHoKhauNhanKhau(idHoKhauNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhauNhanKhau;
	}
	
	
	public static boolean editHoKhauNhanKhau(ModelHoKhauNhanKhau data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.hokhaunhankhau SET idNhanKhau = ?, quanHe = ?"
				+ "WHERE idHoKhauNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());
			ps.setString(2, data.getQuanHe());
			ps.setInt(3, data.getIdHoKhauNhanKhau());

			ps.executeUpdate();
			lsEditHoKhauNhanKhau(data.getIdHoKhauNhanKhau());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static boolean deleteHoKhauNhanKhau(ModelHoKhauNhanKhau data) {
		boolean done = false;

		deleteLsHoKhauNhanKhau(data.getIdHoKhauNhanKhau());

		String query = "DELETE FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idHoKhauNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdHoKhauNhanKhau());

			ps.executeUpdate();
			lsDeleteHoKhauNhanKhau();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelHoKhauNhanKhau> searchHoKhauNhanKhauByIdNhanKhau(int idNhanKhau) {
		List<ModelHoKhauNhanKhau> listHoKhauNhanKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoKhauNhanKhau modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				ModelHoKhau modelHoKhau = queryHoKhauByIdHoKhau(rs.getInt("idHoKhau"));
				modelHoKhauNhanKhau.setModelNhanKhau(modelNhanKhau);
				modelHoKhauNhanKhau.setModelHoKhau(modelHoKhau);
				listHoKhauNhanKhau.add(modelHoKhauNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoKhauNhanKhau;
	}
	
	public static List<ModelHoKhauNhanKhau> searchHoKhauNhanKhauByIdHoKhau(int idHoKhau) {
		List<ModelHoKhauNhanKhau> listHoKhauNhanKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoKhauNhanKhau modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				ModelHoKhau modelHoKhau = queryHoKhauByIdHoKhau(rs.getInt("idHoKhau"));
				modelHoKhauNhanKhau.setModelNhanKhau(modelNhanKhau);
				modelHoKhauNhanKhau.setModelHoKhau(modelHoKhau);
				listHoKhauNhanKhau.add(modelHoKhauNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoKhauNhanKhau;
	}

	private static ModelUser getUser(String taiKhoan, String matKhau) {
		ModelUser modelUser = null;

		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE taiKhoan = ? AND matKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, taiKhoan);
			ps.setString(2, matKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelUser = new ModelUser(rs.getInt("idUser"), rs.getString("tenNguoiDung"), rs.getString("taiKhoan"),
						rs.getString("matKhau"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelUser;
	}
	
	private static ModelHoKhau getHoKhau(int idNhanKhau, String diaChi) {
		ModelHoKhau modelHoKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhau\n" + "WHERE idNhanKhau = ? AND diaChi = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, diaChi);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"),
						rs.getString("diaChi"), rs.getString("soNha"), rs.getString("duongPho"),
						rs.getString("phuong"), rs.getString("quan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhau;
	}
	
	private static ModelHoKhauNhanKhau getHoKhauNhanKhau(int idHoKhau, int idNhanKhau) {
		ModelHoKhauNhanKhau modelHoKhauNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idHoKhau = ? AND idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);
			ps.setInt(2, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhauNhanKhau;
	}
	
	public static boolean addTamTru(int idNhanKhau, String noiTamTru, LocalDate ngayHieuLuc, LocalDate ngayHetHieuLuc,
							String lyDo) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.tamtru (`idNhanKhau`, `noiTamTru`, `ngayHieuLuc`, `ngayHetHieuLuc`,"
				+ "`lyDo`)"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, noiTamTru);
			ps.setDate(3, Date.valueOf(ngayHieuLuc));
			ps.setDate(4, Date.valueOf(ngayHetHieuLuc));
			ps.setString(5, lyDo);

			ps.executeUpdate();
			ModelTamTru modelTamTru = getTamTru(idNhanKhau, noiTamTru);
			lsAddTamTru(modelTamTru.getIdTamTru());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelTamTru getTamTru(int idTamTru) {
		ModelTamTru modelTamTru = null;

		String query = "SELECT * FROM quanlynhankhau.tamtru\n" + "WHERE idTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idTamTru);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelTamTru = new ModelTamTru(rs.getInt("idTamTru"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"), rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamTru.setModelNhanKhau(modelNhanKhau);
				lsGetTamTru(idTamTru);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelTamTru;
	}
	
	public static boolean editTamTru(ModelTamTru data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.tamtru SET idNhanKhau = ?, noiTamTru = ?, ngayHieuLuc = ?,"
				+ "ngayHetHieuLuc = ?, lyDo = ?"
				+ "WHERE idTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());
			ps.setString(2, data.getNoiTamTru());
			ps.setDate(3, data.getNgayHieuLuc());
			ps.setDate(4, data.getNgayHetHieuLuc());
			ps.setString(5, data.getLyDo());
			ps.setInt(6, data.getIdTamTru());

			ps.executeUpdate();
			lsEditTamTru(data.getIdTamTru());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelTamTru> getAllTamTru(){
		List<ModelTamTru> listTamTru = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.tamtru";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelTamTru modelTamTru = new ModelTamTru(rs.getInt("idTamTru"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamTru.setModelNhanKhau(modelNhanKhau);
				listTamTru.add(modelTamTru);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTamTru;
	}
	
	public static boolean deleteTamTru(ModelTamTru data) {
		boolean done = false;

		deleteLsTamTru(data.getIdTamTru());
		deleteTamTruTamVang(data.getIdNhanKhau());

		String query = "DELETE FROM quanlynhankhau.tamtru\n" + "WHERE idTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdTamTru());

			ps.executeUpdate();
			lsDeleteTamTru();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelTamTru> searchTamTruByIdNhanKhau(int idNhanKhau) {
		List<ModelTamTru> listTamTru = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.tamtru\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelTamTru modelTamTru = new ModelTamTru(rs.getInt("idTamTru"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamTru.setModelNhanKhau(modelNhanKhau);
				listTamTru.add(modelTamTru);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTamTru;
	}
	
	public static boolean addTamVang(int idNhanKhau, String noiTamTru, LocalDate ngayHieuLuc, LocalDate ngayHetHieuLuc,
							String lyDo) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.tamvang (`idNhanKhau`, `noiTamTru`, `ngayHieuLuc`, `ngayHetHieuLuc`,"
				+ "`lyDo`)"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, noiTamTru);
			ps.setDate(3, Date.valueOf(ngayHieuLuc));
			ps.setDate(4, Date.valueOf(ngayHetHieuLuc));
			ps.setString(5, lyDo);

			ps.executeUpdate();
			ModelTamVang modelTamVang = getTamVang(idNhanKhau, noiTamTru);
			lsAddTamVang(modelTamVang.getIdTamVang());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelTamVang getTamVang(int idTamVang) {
		ModelTamVang modelTamVang = null;

		String query = "SELECT * FROM quanlynhankhau.tamvang\n" + "WHERE idTamVang = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idTamVang);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelTamVang = new ModelTamVang(rs.getInt("idTamVang"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"), rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamVang.setModelNhanKhau(modelNhanKhau);
				lsGetTamVang(idTamVang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelTamVang;
	}
	
	public static boolean editTamVang(ModelTamVang data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.tamvang SET idNhanKhau = ?, noiTamTru = ?, ngayHieuLuc = ?,"
				+ "ngayHetHieuLuc = ?, lyDo = ?"
				+ "WHERE idTamVang = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());
			ps.setString(2, data.getNoiTamTru());
			ps.setDate(3, data.getNgayHieuLuc());
			ps.setDate(4, data.getNgayHetHieuLuc());
			ps.setString(5, data.getLyDo());
			ps.setInt(6, data.getIdTamVang());

			ps.executeUpdate();
			lsEditTamVang(data.getIdTamVang());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelTamVang> getAllTamVang(){
		List<ModelTamVang> listTamVang = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.tamvang";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelTamVang modelTamVang = new ModelTamVang(rs.getInt("idTamVang"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamVang.setModelNhanKhau(modelNhanKhau);
				listTamVang.add(modelTamVang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTamVang;
	}
	

	public static boolean deleteTamVang(ModelTamVang data) {
		boolean done = false;

		deleteLsTamVang(data.getIdTamVang());
		deleteTamTruTamVang(data.getIdNhanKhau());

		String query = "DELETE FROM quanlynhankhau.tamvang\n" + "WHERE idTamVang = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdTamVang());

			ps.executeUpdate();
			lsDeleteTamVang();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelTamVang> searchTamVangByIdNhanKhau(int idNhanKhau) {
		List<ModelTamVang> listTamVang = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.tamvang\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelTamVang modelTamVang = new ModelTamVang(rs.getInt("idTamVang"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelTamVang.setModelNhanKhau(modelNhanKhau);
				listTamVang.add(modelTamVang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTamVang;
	}
	
	private static ModelTamTru getTamTru(int idNhanKhau, String noiTamTru) {
		ModelTamTru modelTamTru = null;

		String query = "SELECT * FROM quanlynhankhau.tamtru\n" + "WHERE idNhanKhau = ? AND noiTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, noiTamTru);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelTamTru = new ModelTamTru(rs.getInt("idTamTru"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelTamTru;
	}
	
	private static ModelTamVang getTamVang(int idNhanKhau, String noiTamTru) {
		ModelTamVang modelTamVang = null;

		String query = "SELECT * FROM quanlynhankhau.tamvang\n" + "WHERE idNhanKhau = ? AND noiTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, noiTamTru);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelTamVang = new ModelTamVang(rs.getInt("idTamVang"), rs.getInt("idNhanKhau"),
						rs.getString("noiTamTru"),rs.getDate("ngayHieuLuc"), rs.getDate("ngayHetHieuLuc"),
						rs.getString("lyDo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelTamVang;
	}
	
	
	public static boolean addTamTruToTamTruTamVang(int idNhanKhau) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.tamtrutamvang (`idNhanKhau`, `trangThai`)"
				+ " VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, "Tạm Trú");

			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static boolean addTamVangToTamTruTamVang(int idNhanKhau) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.tamtrutamvang (`idNhanKhau`, `trangThai`)"
				+ " VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, "Tạm Vắng");

			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static void deleteTamTruTamVang(int idNhanKhau) {
		String query = "DELETE FROM quanlynhankhau.tamtrutamvang\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void lsSignIn() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "dangNhap");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void lsSignUp(int idUser) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "dangKy");
			ps.setInt(2, idUser);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void lsAddNhanKhau(int idNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "themNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void lsGetNhanKhau(int idNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xemNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	private static void lsGetAllNhanKhau() {
//		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
//		PreparedStatement ps;
//		try {
//			ps = connection.prepareStatement(query);
//			ps.setString(1, "xemtoanboNhanKhau");
//			ps.setInt(2, currentUser.getIdUser());
//			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	private static void lsEditNhanKhau(int idNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "suaNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void lsDeleteNhanKhau() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xoaNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddHoKhau(int idHoKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "themHoKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetHoKhau(int idHoKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xemHoKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsEditHoKhau(int idHoKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "suaHoKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteHoKhau() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xoaHoKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddHoKhauNhanKhau(int idHoKhauNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhauNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "themHoKhauNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhauNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetHoKhauNhanKhau(int idHoKhauNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhauNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xemHoKhauNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhauNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsEditHoKhauNhanKhau(int idHoKhauNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhauNhanKhau`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "suaHoKhauNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhauNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteHoKhauNhanKhau() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xoaHoKhauNhanKhau");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteLsNhanKhau(int idNhanKhau) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteLsHoKhau(int idHoKhau) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteLsHoKhauNhanKhau(int idHoKhauNhanKhau) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idHoKhauNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhauNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddTamTru(int idTamTru) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamTru`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "themTamTru");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamTru);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetTamTru(int idTamTru) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamTru`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xemTamTru");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamTru);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsEditTamTru(int idTamTru) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamTru`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "suaTamTru");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamTru);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteTamTru() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xoaTamTru");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteLsTamTru(int idTamTru) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idTamTru);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddTamVang(int idTamVang) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamVang`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "themTamVang");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamVang);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetTamVang(int idTamVang) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamTru`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xemTamVang");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamVang);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsEditTamVang(int idTamVang) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idTamVang`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "suaTamVang");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idTamVang);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteTamVang() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "xoaTamVang");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteLsTamVang(int idTamVang) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idTamVang = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idTamVang);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void IsChangeNoiThuongTruNhanKhau(int idNhanKhau, String diaChi) {
		String query = "UPDATE quanlynhankhau.nhankhau SET noiThuongTru = ?, ngayDangKyThuongTru = ?\"\r\n"
				+ "				+ \"WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, diaChi);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, idNhanKhau);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
