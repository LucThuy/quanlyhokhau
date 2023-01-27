package cnpm.QuanLyNhanKhau;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoatDong;
import cnpm.QuanLyNhanKhau.model.ModelLichSu;
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelTamTru;
import cnpm.QuanLyNhanKhau.model.ModelTamVang;
import cnpm.QuanLyNhanKhau.model.ModelUser;

public class Connector {

	private static String url = "jdbc:mysql://localhost:3306/quanlynhankhau";
	private static String user = "root";
//	private static String password = "Minh_0112";
	
//	private static String url = "jdbc:mysql://localhost:3306/quanlynhankhau";
//	private static String user = "root";
	private static String password = "namanh202";

	public static ModelUser currentUser;

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

	public static boolean signUp(String taiKhoan, String matKhau, String tenNguoiDung, String chucVu) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.user (`taiKhoan`, `matKhau`, `tenNguoiDung`, `role`,"
				+ "`capQuyen`) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, taiKhoan);
			ps.setString(2, matKhau);
			ps.setString(3, tenNguoiDung);
			ps.setString(4, chucVu);
			ps.setString(5, "Chưa cấp quyền");
			

			ps.executeUpdate();
			ModelUser modelUser = getUser(taiKhoan, matKhau);
			lsSignUp(modelUser.getIdUser());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelUser queryUserByTaiKhoanAndMatKhau(String taiKhoan, String matKhau) {
		ModelUser modelUser = null;

		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE taiKhoan = ? AND matKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, taiKhoan);
			ps.setString(2, matKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelUser = new ModelUser(rs.getInt("idUser"), rs.getString("tenNguoiDung"),						rs.getString("taiKhoan"),
						rs.getString("matKhau"), rs.getString("role"), rs.getString("capQuyen"));
				currentUser = modelUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return modelUser;
	}
	
	public static ModelUser queryUserByIdUser(int idUser) {
		ModelUser modelUser = null;

		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelUser = new ModelUser(rs.getInt("idUser"), rs.getString("tenNguoiDung"),						rs.getString("taiKhoan"),
						rs.getString("matKhau"), rs.getString("role"), rs.getString("capQuyen"));
				currentUser = modelUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return modelUser;
	}
	
	
	public static boolean editUser(ModelUser data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.user SET tenNguoiDung = ?, taiKhoan = ?, matKhau = ?, role = ?, capQuyen = ?\n"
				+ "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, data.getTenNguoiDung());
			ps.setString(2, data.getTaiKhoan());
			ps.setString(3, data.getMatKhau());
			ps.setString(4, data.getRole());
			ps.setString(5, data.getCapQuyen());
			
			ps.setInt(6, data.getIdUser());
			
			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static boolean deleteUser(ModelUser data) {
		boolean done = false;

		deleteLsUser(data.getIdUser());
		
		String query = "DELETE FROM quanlynhankhau.user\n" + "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdUser());

			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static ModelUser getUser(int idUser) {
		ModelUser modelUser = null;

		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelUser = new ModelUser(rs.getInt("idUser"),rs.getString("tenNguoiDung"),
						rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("role"),
						rs.getString("capQuyen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelUser;
	}
	
	public static List<ModelUser> getAllUser() {
		List<ModelUser> listUser = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.user";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelUser modelUser = new ModelUser(rs.getInt("idUser"),rs.getString("tenNguoiDung"),
						rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("role"),
						rs.getString("capQuyen"));
				listUser.add(modelUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUser;
	}
	
	public static List<ModelUser> getAllUserByCapQuyen(String capQuyen) {
		List<ModelUser> listUser = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE capQuyen = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, capQuyen);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelUser modelUser = new ModelUser(rs.getInt("idUser"),rs.getString("tenNguoiDung"),
						rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("role"),
						rs.getString("capQuyen"));
				listUser.add(modelUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUser;
	}
	
	public static List<ModelUser> getAllUserExceptToTruong() {
		List<ModelUser> listUser = new ArrayList<>();
		
		String query = "SELECT * FROM quanlynhankhau.user\n" + "WHERE role != ? AND capQuyen = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Tổ Trưởng");
			ps.setString(2, "Đã cấp quyền");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelUser modelUser = new ModelUser(rs.getInt("idUser"),rs.getString("tenNguoiDung"),
						rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("role"),
						rs.getString("capQuyen"));
				listUser.add(modelUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUser;
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
				+ " `trinhDoNgoaiNgu`, `ngheNghiep`, `noiLamViec`, `tonGiao`, `quocTich`, `trinhDoChuyenMon`, `ghiChu`,"
				+ "`trangThai`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps.setString(20, "Vô gia cư");

			ps.executeUpdate();
			ModelNhanKhau modelNhanKhau = queryNhanKhauByHoTenAndNgaySinhAndCccd(hoTen, ngaySinh, cccd);
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
	}
	
	public static ModelNhanKhau queryNhanKhauByHoTenAndNgaySinhAndCccd(String hoTen, LocalDate ngaySinh, String cccd) {
		ModelNhanKhau modelNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.nhankhau\n" + "WHERE hoTen = ? AND ngaySinh = ? AND cccd = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hoTen);
			ps.setDate(2, Date.valueOf(ngaySinh));
			ps.setString(3, cccd);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhanKhau = new ModelNhanKhau(rs.getInt("idNhanKhau"), rs.getString("hoTen"),
						rs.getString("biDanh"), rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"),
						rs.getDate("ngayCap"), rs.getString("noiCap"), rs.getString("nguyenQuan"),rs.getString("danToc"),
						rs.getString("noiThuongTru"), rs.getDate("ngayDangKyThuongTru"), rs.getString("trinhDoHocVan"),
						rs.getString("trinhDoNgoaiNgu"), rs.getString("ngheNghiep"), rs.getString("noiLamViec"),
						rs.getString("tonGiao"), rs.getString("quocTich"), rs.getString("trinhDoChuyenMon"),
						rs.getString("ghiChu"), rs.getString("trangThai"));
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
//		deleteLsTamTru(data.getIdNhanKhau());
//		deleteLsTamVang(data.getIdNhanKhau());
//		deleteLsHoatDong(data.getIdNhanKhau());

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
						rs.getString("ghiChu"), rs.getString("trangThai"));
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
				listNhanKhau.add(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNhanKhau;
	}
	
	public static ModelNhanKhau searchNhanKhauByIdNhanKhau(int idNhanKhau) {
		ModelNhanKhau modelNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.nhankhau WHERE idNhanKhau = ?";
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
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
						rs.getString("ghiChu"), rs.getString("trangThai"));
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
			updateTrangThai(idNhanKhau, true);
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

		if(data != null) {
			deleteLsHoKhau(data.getIdHoKhau());

			String query = "DELETE FROM quanlynhankhau.hokhau\n" + "WHERE idHoKhau = ?";
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(query);
				ps.setInt(1, data.getIdHoKhau());

				ps.executeUpdate();
				lsDeleteHoKhau();
				updateTrangThai(data.getIdNhanKhau(), false);
				done = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	
	public static void updateTrangThai(int idNhanKhau, boolean addHoKhau) {
		if(!searchNhanKhauByIdNhanKhau(idNhanKhau).getTrangThai().equals("Tạm Vắng") && 
				!searchNhanKhauByIdNhanKhau(idNhanKhau).getTrangThai().equals("Tạm Trú")) {
			String query = "UPDATE quanlynhankhau.nhankhau SET trangThai = ?"
				+ "WHERE idNhanKhau = ?";
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(query);
				if(addHoKhau) {
					ps.setString(1, "Có Hộ Khẩu");
				}
				else {
				ps.setString(1, "Vô Gia Cư");
				}
				ps.setInt(2, idNhanKhau);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
			lsAddHoKhauNhanKhau(idHoKhau, idNhanKhau);
			ModelHoKhau modelHoKhau = getHoKhau(idHoKhau);
			IsChangeNoiThuongTruNhanKhau(idNhanKhau, modelHoKhau.getDiaChi());
			updateTrangThai(idNhanKhau, true);
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
		
		if(data != null) {
			deleteLsHoKhauNhanKhau(data.getIdHoKhauNhanKhau());
			String query = "DELETE FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idHoKhauNhanKhau = ?";
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(query);
				ps.setInt(1, data.getIdHoKhauNhanKhau());

				ps.executeUpdate();
				lsDeleteHoKhauNhanKhau(data.getIdHoKhau(), data.getIdNhanKhau());
				updateTrangThai(data.getIdNhanKhau(), false);
				done = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return done;
	}
	
	public static ModelHoKhauNhanKhau searchHoKhauNhanKhauByIdNhanKhau(int idNhanKhau) {
		ModelHoKhauNhanKhau modelHoKhauNhanKhau = null;

		String query = "SELECT * FROM quanlynhankhau.hokhaunhankhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				ModelHoKhau modelHoKhau = queryHoKhauByIdHoKhau(rs.getInt("idHoKhau"));
				modelHoKhauNhanKhau.setModelNhanKhau(modelNhanKhau);
				modelHoKhauNhanKhau.setModelHoKhau(modelHoKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoKhauNhanKhau;
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
				modelUser = new ModelUser(rs.getInt("idUser"),rs.getString("tenNguoiDung"),
						rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("role"),
						rs.getString("capQuyen"));
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
			updateTrangThaiTamTru(idNhanKhau, true);
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

		String query = "DELETE FROM quanlynhankhau.tamtru\n" + "WHERE idTamTru = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdTamTru());

			ps.executeUpdate();
			lsDeleteTamTru();
			updateTrangThaiTamTru(data.getIdNhanKhau(), false);
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static void updateTrangThaiTamTru(int idNhanKhau, boolean addTamTru) {

		String query = "UPDATE quanlynhankhau.nhankhau SET trangThai = ?"
				+ "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			if(addTamTru) {
				ps.setString(1, "Tạm Trú");
			}
			else {
				if(searchHoKhauNhanKhauByIdNhanKhau(idNhanKhau) == null) {
					ps.setString(1, "Vô Gia Cư");
				}
				else{
					ps.setString(1, "Có Hộ Khẩu");
				}
			}
			ps.setInt(2, idNhanKhau);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			updateTrangThaiTamVang(idNhanKhau, true);
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

		String query = "DELETE FROM quanlynhankhau.tamvang\n" + "WHERE idTamVang = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdTamVang());

			ps.executeUpdate();
			lsDeleteTamVang();
			updateTrangThaiTamVang(data.getIdNhanKhau(), false);
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static void updateTrangThaiTamVang(int idNhanKhau, boolean addTamVang) {

		String query = "UPDATE quanlynhankhau.nhankhau SET trangThai = ?"
				+ "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			if(addTamVang) {
				ps.setString(1, "Tạm Vắng");
			}
			else {
				if(searchHoKhauNhanKhauByIdNhanKhau(idNhanKhau) == null) {
					ps.setString(1, "Vô Gia Cư");
				}
				else{
					ps.setString(1, "Có Hộ Khẩu");
				}
			}
			ps.setInt(2, idNhanKhau);
			

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public static boolean addQuanLyNhaVanHoa(int soLuongBan, String hienTrangBan,
			int soLuongGhe, String hienTrangGhe, int soLuongLoa, String hienTrangLoa, int soLuongDai,
			String hienTrangDai, int soLuongManHinh, String hienTrangManHinh, int soLuongDen,
			String hienTrangDen) {
		
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.nhavanhoa (`idUser`, `ngayKiemTra`, `soLuongBan`,"
				+ " `hienTrangBan`, `soLuongGhe`, `hienTrangGhe`, `soLuongLoa`, `hienTrangLoa`,"
				+ " `soLuongDai`, `hienTrangDai`, `soLuongManHinh`, `hienTrangManHinh`,"
				+ " `soLuongDen`, `hienTrangDen`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String now = sdf3.format(time);
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, currentUser.getIdUser());
			ps.setString(2, now);
			ps.setInt(3, soLuongBan);
			ps.setString(4, hienTrangBan);
			ps.setInt(5, soLuongGhe);
			ps.setString(6, hienTrangGhe);
			ps.setInt(7, soLuongLoa);
			ps.setString(8, hienTrangLoa);
			ps.setInt(9, soLuongDai);
			ps.setString(10, hienTrangDai);
			ps.setInt(11, soLuongManHinh);
			ps.setString(12, hienTrangManHinh);
			ps.setInt(13, soLuongDen);
			ps.setString(14, hienTrangDen);


			ps.executeUpdate();
			ModelNhaVanHoa modelNhaVanHoa = getQuanLyNhaVanHoa(currentUser.getIdUser(), now);
			lsAddQuanLyNhaVanHoa(modelNhaVanHoa.getIdKiemTra());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static ModelNhaVanHoa getQuanLyNhaVanHoa(int idKiemTra) {
		ModelNhaVanHoa modelNhaVanHoa = null;

		String query = "SELECT * FROM quanlynhankhau.nhavanhoa\n" + "WHERE idKiemTra = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idKiemTra);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhaVanHoa = new ModelNhaVanHoa(rs.getInt("idKiemTra"), rs.getInt("idUser"),
						rs.getDate("ngayKiemTra"), rs.getInt("soLuongBan"), rs.getString("hienTrangBan"),
						rs.getInt("soLuongGhe"), rs.getString("hienTrangGhe"), rs.getInt("soLuongLoa"),
						rs.getString("hienTrangLoa"), rs.getInt("soLuongDai"), rs.getString("hienTrangDai"),
						rs.getInt("soLuongManHinh"), rs.getString("hienTrangManHinh"),
						rs.getInt("soLuongDen"), rs.getString("hienTrangDen"));
				lsGetQuanLyNhaVanHoa(idKiemTra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhaVanHoa;
	}
	
	public static List<ModelNhaVanHoa> getAllQuanLyNhaVanHoaByIdUser(int idUser) {
		List<ModelNhaVanHoa> listQuanLy = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.nhavanhoa\n" + "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelNhaVanHoa modelNhaVanHoa = new ModelNhaVanHoa(rs.getInt("idKiemTra"), rs.getInt("idUser"),
						rs.getDate("ngayKiemTra"), rs.getInt("soLuongBan"), rs.getString("hienTrangBan"),
						rs.getInt("soLuongGhe"), rs.getString("hienTrangGhe"), rs.getInt("soLuongLoa"),
						rs.getString("hienTrangLoa"), rs.getInt("soLuongDai"), rs.getString("hienTrangDai"),
						rs.getInt("soLuongManHinh"), rs.getString("hienTrangManHinh"),
						rs.getInt("soLuongDen"), rs.getString("hienTrangDen"));
//				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
//				modelTamTru.setModelNhanKhau(modelNhanKhau);
				listQuanLy.add(modelNhaVanHoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listQuanLy;
	}
	
	public static List<ModelNhaVanHoa> getAllQuanLyNhaVanHoa() {
		List<ModelNhaVanHoa> listQuanLy = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.nhavanhoa";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelNhaVanHoa modelNhaVanHoa = new ModelNhaVanHoa(rs.getInt("idKiemTra"), rs.getInt("idUser"),
						rs.getDate("ngayKiemTra"), rs.getInt("soLuongBan"), rs.getString("hienTrangBan"),
						rs.getInt("soLuongGhe"), rs.getString("hienTrangGhe"), rs.getInt("soLuongLoa"),
						rs.getString("hienTrangLoa"), rs.getInt("soLuongDai"), rs.getString("hienTrangDai"),
						rs.getInt("soLuongManHinh"), rs.getString("hienTrangManHinh"),
						rs.getInt("soLuongDen"), rs.getString("hienTrangDen"));
				ModelUser modelUser = queryUserByIdUser(rs.getInt("idUser"));
				modelNhaVanHoa.setModelUser(modelUser);
				listQuanLy.add(modelNhaVanHoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listQuanLy;
	}
	
	public static ModelNhaVanHoa getTheLatestNhaVanHoa() {
		ModelNhaVanHoa modelNhaVanHoa = null;
		String query = "SELECT * FROM quanlynhankhau.nhavanhoa";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				modelNhaVanHoa = new ModelNhaVanHoa(rs.getInt("idKiemTra"), rs.getInt("idUser"),
						rs.getDate("ngayKiemTra"), rs.getInt("soLuongBan"), rs.getString("hienTrangBan"),
						rs.getInt("soLuongGhe"), rs.getString("hienTrangGhe"), rs.getInt("soLuongLoa"),
						rs.getString("hienTrangLoa"), rs.getInt("soLuongDai"), rs.getString("hienTrangDai"),
						rs.getInt("soLuongManHinh"), rs.getString("hienTrangManHinh"),
						rs.getInt("soLuongDen"), rs.getString("hienTrangDen"));
				ModelUser modelUser = queryUserByIdUser(rs.getInt("idUser"));
				modelNhaVanHoa.setModelUser(modelUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelNhaVanHoa;
	}
	
	public static boolean deleteQuanLyNhaVanHoa(ModelNhaVanHoa data) {
		boolean done = false;

		deleteLsQuanLyNhaVanHoa(data.getIdKiemTra());


		String query = "DELETE FROM quanlynhankhau.nhavanhoa\n" + "WHERE idKiemTra = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdKiemTra());

			ps.executeUpdate();
			lsDeleteQuanLyNhaVanHoa();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static boolean addHoatDong(int idNhanKhau, String hoatDong, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, int soLuongBan, int soLuongGhe, int soLuongLoa, int soLuongDai,
			int soLuongManHinh, int soLuongDen) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.hoatdong (`idNhanKhau`, `hoatDong`, `ngayBatDau`,"
				+ " `ngayKetThuc`, `soLuongBan`, `soLuongGhe`, `soLuongLoa`, `soLuongDai`,"
				+ " `soLuongManHinh`, `soLuongDen`, `lePhi`, `xacNhan`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, hoatDong);
			ps.setDate(3, Date.valueOf(ngayBatDau));
			ps.setDate(4, Date.valueOf(ngayKetThuc));
			ps.setInt(5, soLuongBan);
			ps.setInt(6, soLuongGhe);
			ps.setInt(7, soLuongLoa);
			ps.setInt(8, soLuongDai);
			ps.setInt(9, soLuongManHinh);
			ps.setInt(10, soLuongDen);
			ps.setString(11, calLePhi(ngayBatDau, ngayKetThuc, soLuongBan, soLuongGhe, soLuongLoa,
					soLuongDai, soLuongManHinh, soLuongDen));
			ps.setString(12, "Chờ xác nhận");

			ps.executeUpdate();
			ModelHoatDong modelHoatDong = getHoatDong(idNhanKhau, hoatDong);
			lsAddHoatDong(modelHoatDong.getIdHoatDong());
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	
	
	public static ModelHoatDong getHoatDong(int idHoatDong) {
		ModelHoatDong modelHoatDong = null;

		String query = "SELECT * FROM quanlynhankhau.hoatdong\n" + "WHERE idHoatDong = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoatDong);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoatDong =  new ModelHoatDong(rs.getInt("idHoatDong"),rs.getInt("idNhanKhau"),
						rs.getDate("ngayBatDau"), rs.getDate("ngayKetThuc"), rs.getString("hoatDong"),
						rs.getInt("soLuongBan"), rs.getInt("soLuongGhe"), rs.getInt("soLuongLoa"),
						rs.getInt("soLuongDai"), rs.getInt("soLuongManHinh"), rs.getInt("soLuongDen"),
						rs.getString("lePhi"), rs.getString("ghiChu"), rs.getString("xacNhan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoatDong.setModelNhanKhau(modelNhanKhau);
				lsGetHoatDong(idHoatDong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoatDong;
	}
	
	
	public static boolean editHoatDong(ModelHoatDong data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.hoatDong SET  xacNhan = ?\n"
				+ "WHERE idHoatDong = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, data.getXacNhan());
			
			ps.setInt(2, data.getIdHoatDong());
			
			ps.executeUpdate();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static boolean deleteHoatDong(ModelHoatDong data) {
		boolean done = false;

		deleteLsHoatDong(data.getIdHoatDong());

		String query = "DELETE FROM quanlynhankhau.hoatDong\n" + "WHERE idHoatDong = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdHoatDong());

			ps.executeUpdate();
			lsDeleteHoatDong();
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}
	
	public static List<ModelHoatDong> getAllHoatDong() {
		List<ModelHoatDong> listHoatDong = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hoatdong";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
//			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoatDong modelHoatDong =  new ModelHoatDong(rs.getInt("idHoatDong"),
						rs.getInt("idNhanKhau"), rs.getDate("ngayBatDau"), rs.getDate("ngayKetThuc"),
						rs.getString("hoatDong"), rs.getInt("soLuongBan"), rs.getInt("soLuongGhe"), 
						rs.getInt("soLuongLoa"), rs.getInt("soLuongDai"), rs.getInt("soLuongManHinh"), 
						rs.getInt("soLuongDen"), rs.getString("lePhi"), rs.getString("ghiChu"),
						rs.getString("xacNhan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoatDong.setModelNhanKhau(modelNhanKhau);
				listHoatDong.add(modelHoatDong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoatDong;
	}
	
	public static List<ModelHoatDong> searchHoatDongByIdNhanKhau(int idNhanKhau) {
		List<ModelHoatDong> listHoatDong = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hoatdong\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoatDong modelHoatDong =  new ModelHoatDong(rs.getInt("idHoatDong"),
						rs.getInt("idNhanKhau"), rs.getDate("ngayBatDau"), rs.getDate("ngayKetThuc"),
						rs.getString("hoatDong"), rs.getInt("soLuongBan"), rs.getInt("soLuongGhe"), 
						rs.getInt("soLuongLoa"), rs.getInt("soLuongDai"), rs.getInt("soLuongManHinh"), 
						rs.getInt("soLuongDen"), rs.getString("lePhi"), rs.getString("ghiChu"),
						rs.getString("xacNhan"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoatDong.setModelNhanKhau(modelNhanKhau);
				listHoatDong.add(modelHoatDong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoatDong;
	}
	
	private static ModelNhaVanHoa getQuanLyNhaVanHoa(int idUser, String ngayKiemTra) {
		ModelNhaVanHoa modelNhaVanHoa = null;
		String query = "SELECT * FROM quanlynhankhau.nhavanhoa\r\n"
				+ "Where idUser = ? AND ngayKiemTra = ? ";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);
			ps.setString(2, ngayKiemTra);
			

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelNhaVanHoa = new ModelNhaVanHoa(rs.getInt("idKiemTra"), rs.getInt("idUser"),
						rs.getDate("ngayKiemTra"), rs.getInt("soLuongBan"), rs.getString("hienTrangBan"),
						rs.getInt("soLuongGhe"), rs.getString("hienTrangGhe"), rs.getInt("soLuongLoa"),
						rs.getString("hienTrangLoa"), rs.getInt("soLuongDai"), rs.getString("hienTrangDai"),
						rs.getInt("soLuongManHinh"), rs.getString("hienTrangManHinh"),
						rs.getInt("soLuongDen"), rs.getString("hienTrangDen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhaVanHoa;
	}
	
	private static ModelHoatDong getHoatDong(int idNhanKhau, String hoatDong) {
		ModelHoatDong modelHoatDong = null;

		String query = "SELECT * FROM quanlynhankhau.hoatdong\n" + "WHERE idNhanKhau = ? AND hoatDong = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, hoatDong);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelHoatDong =  new ModelHoatDong(rs.getInt("idHoatDong"),
						rs.getInt("idNhanKhau"), rs.getDate("ngayBatDau"), rs.getDate("ngayKetThuc"),
						rs.getString("hoatDong"), rs.getInt("soLuongBan"), rs.getInt("soLuongGhe"), 
						rs.getInt("soLuongLoa"), rs.getInt("soLuongDai"), rs.getInt("soLuongManHinh"), 
						rs.getInt("soLuongDen"), rs.getString("lePhi"), rs.getString("ghiChu"),
						rs.getString("xacNhan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelHoatDong;
	}
	
	private static void lsSignIn() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Đăng nhập");
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
			ps.setString(1, "Đăng Ký");
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
			ps.setString(1, "Thêm nhân khẩu");
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
			ps.setString(1, "Xem nhân khẩu");
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
			ps.setString(1, "Sửa nhân khẩu");
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
			ps.setString(1, "Xoá nhân khẩu");
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
			ps.setString(1, "Thêm hộ khẩu");
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
			ps.setString(1, "Xem hộ khẩu");
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
			ps.setString(1, "Sửa hộ khẩu");
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
			ps.setString(1, "Xoá hộ khẩu");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddHoKhauNhanKhau(int idHoKhau, int idNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoKhau`, `idNhanKhau`)"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Thêm hộ khẩu nhân khẩu");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhau);
			ps.setInt(5, idNhanKhau);

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
			ps.setString(1, "Xem hộ khẩu nhân khẩu");
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
			ps.setString(1, "Sửa hộ khẩu nhân khẩu");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoKhauNhanKhau);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteHoKhauNhanKhau(int idHoKhau, int idNhanKhau) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idNhanKhau`, `idHoKhau`)"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Xoá hộ khẩu nhân khẩu");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idNhanKhau);
			ps.setInt(5, idHoKhau);

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
			ps.setString(1, "Thêm tạm trú");
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
			ps.setString(1, "Xem tạm trú");
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
			ps.setString(1, "Sửa tạm trú");
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
			ps.setString(1, "Xoá tạm trú");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

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
			ps.setString(1, "Thêm tạm vắng");
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
			ps.setString(1, "Xem tạm vắng");
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
			ps.setString(1, "Sửa tạm vắng");
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
			ps.setString(1, "Xoá tạm vắng");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddQuanLyNhaVanHoa(int idKiemTra) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idKiemTra`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Kiểm tra hiện trạng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idKiemTra);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetQuanLyNhaVanHoa(int idKiemTra) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idKiemTra`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Xem chi tiết hiện trạng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idKiemTra);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteQuanLyNhaVanHoa() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Xoá hiện trạng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsAddHoatDong(int idHoatDong) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoatDong`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Đăng ký sử dụng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoatDong);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsGetHoatDong(int idHoatDong) {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idHoatDong`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Xem chi tiết hoạt động sử dụng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, idHoatDong);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void lsDeleteHoatDong() {
		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "Xoá hoạt động sử dụng nhà văn hoá");
			ps.setInt(2, currentUser.getIdUser());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteLsUser(int idUser) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idUser = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);
	
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
	
	private static void deleteLsQuanLyNhaVanHoa(int idKiemTra) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idKiemTra = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idKiemTra);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteLsHoatDong(int idHoatDong) {
		String query = "DELETE FROM quanlynhankhau.lichsu\n" + "WHERE idHoatDong = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoatDong);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	private static void lsAddQuanLy(int idKiemTra) {
//		String query = "INSERT INTO quanlynhankhau.lichsu (`thaoTac`, `idUser`, `thoiGian`, `idKiemTra`) VALUES (?, ?, ?, ?)";
//		PreparedStatement ps;
//		try {
//			ps = connection.prepareStatement(query);
//			ps.setString(1, "ThemQuanLy");
//			ps.setInt(2, currentUser.getIdUser());
//			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//			ps.setInt(4, idKiemTra);
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static ModelLichSu getLichSu(int idLichSu) {
		ModelLichSu modelLichSu = null;

		String query = "SELECT * FROM quanlynhankhau.lichsu\n" + "WHERE idLichSu = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idLichSu);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				modelLichSu = new ModelLichSu(rs.getInt("idLichSu"), rs.getString("thaoTac"),
						rs.getInt("idUser"), rs.getTimestamp("thoiGian"));
				ModelUser modelUser = getUser(rs.getInt("idUser"));
//				modelLichSu.setModelUser(modelUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelLichSu;
	}
	
	public static List<ModelLichSu> searchLichSuByIdUser(int idUser) {
		List<ModelLichSu> listLichSu = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.lichSu\n"
				+ "WHERE idUser = ?";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelLichSu modelLichSu = new ModelLichSu(rs.getInt("idLichSu"), rs.getString("thaoTac"),
						rs.getInt("idUser"), rs.getTimestamp("thoiGian"));
				listLichSu.add(modelLichSu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listLichSu;
	}
	
	public static List<ModelLichSu> searchLichSuThayDoiHoKhauByIdHoKhau(int idHoKhau) {
		List<ModelLichSu> listLichSu = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.lichSu\n"
				+ "WHERE idHoKhau = ? AND thaoTac != ? AND thaoTac != ? AND thaoTac != ?";
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idHoKhau);
			ps.setString(2, "Xem hộ khẩu");
			ps.setString(3, "Thêm hộ khẩu");
			ps.setString(4, "Sửa hộ khẩu");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelLichSu modelLichSu = new ModelLichSu(rs.getInt("idLichSu"), rs.getString("thaoTac"),
						rs.getInt("idUser"), rs.getTimestamp("thoiGian"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelLichSu.setModelNhanKhau(modelNhanKhau);
				modelLichSu.setIdHoKhau(idHoKhau);
				listLichSu.add(modelLichSu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listLichSu;
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
	
	public static String calLePhi(LocalDate ngayBatDau, LocalDate ngayKetThuc, int soLuongBan,
			int soLuongGhe, int soLuongLoa, int soLuongDai, int soLuongManHinh, int soLuongDen) {
		
		long Time = ngayKetThuc.getDayOfYear() - ngayBatDau.getDayOfYear();
		long lePhiUocTinh = Time * 3000 + soLuongBan * 100 + soLuongGhe * 30 + soLuongLoa * 1000
				+ soLuongDai * 1000 + soLuongManHinh * 1000 + soLuongDen * 500;
		if(lePhiUocTinh > 50000) {
        	return "50.000.000 VND";
        }
		else if(lePhiUocTinh > 49000) {
        	return "49.000.000 VND";
        }
		else if(lePhiUocTinh > 48000) {
        	return "48.000.000 VND";
        }
		else if(lePhiUocTinh > 47000) {
        	return "47.000.000 VND";
        }
		else if(lePhiUocTinh > 46000) {
        	return "46.000.000 VND";
        }
		else if(lePhiUocTinh > 45000) {
        	return "45.000.000 VND";
        }
		else if(lePhiUocTinh > 44000) {
        	return "44.000.000 VND";
        }
		else if(lePhiUocTinh > 43000) {
        	return "43.000.000 VND";
        }
		else if(lePhiUocTinh > 42000) {
        	return "42.000.000 VND";
        }
		else if(lePhiUocTinh > 41000) {
        	return "41.000.000 VND";
        }
		else if(lePhiUocTinh > 40000) {
        	return "40.000.000 VND";
        }
		else if(lePhiUocTinh > 39000) {
        	return "39.000.000 VND";
        }
		else if(lePhiUocTinh > 38000) {
        	return "38.000.000 VND";
        }
		else if(lePhiUocTinh > 37000) {
        	return "37.000.000 VND";
        }
		else if(lePhiUocTinh > 36000) {
        	return "36.000.000 VND";
        }
		else if(lePhiUocTinh > 35000) {
        	return "35.000.000 VND";
        }
		else if(lePhiUocTinh > 34000) {
        	return "34.000.000 VND";
        }
		else if(lePhiUocTinh > 33000) {
        	return "33.000.000 VND";
        }
		else if(lePhiUocTinh > 32000) {
        	return "32.000.000 VND";
        }
		else if(lePhiUocTinh > 31000) {
        	return "31.000.000 VND";
        }
		else if(lePhiUocTinh > 30000) {
        	return "30.000.000 VND";
        }
		else if(lePhiUocTinh > 29000) {
        	return "29.000.000 VND";
        }
		else if(lePhiUocTinh > 28000) {
        	return "28.000.000 VND";
        }
		else if(lePhiUocTinh > 27000) {
        	return "27.000.000 VND";
        }
		else if(lePhiUocTinh > 26000) {
        	return "26.000.000 VND";
        }
		else if(lePhiUocTinh > 25000) {
        	return "28.000.000 VND";
        }
		else if(lePhiUocTinh > 24000) {
        	return "24.000.000 VND";
        }
		else if(lePhiUocTinh > 23000) {
        	return "23.000.000 VND";
        }
		else if(lePhiUocTinh > 22000) {
        	return "22.000.000 VND";
        }
		else if(lePhiUocTinh > 21000) {
        	return "21.000.000 VND";
        }
		else if(lePhiUocTinh > 20000) {
        	return "20.000.000 VND";
        }
		else if(lePhiUocTinh > 19000) {
        	return "19.000.000 VND";
        }
        else if(lePhiUocTinh > 18000) {
        	return "18.000.000 VND";
        }
        else if(lePhiUocTinh > 17000) {
        	return "17.000.000 VND";
        }
        else if(lePhiUocTinh > 16000) {
        	return "16.000.000 VND";
        }
        else if(lePhiUocTinh > 15000) {
        	return "15.000.000 VND";
        }
        else if(lePhiUocTinh > 14000) {
        	return "14.000.000 VND";
        }
        else if(lePhiUocTinh > 13000) {
        	return "13.000.000 VND";
        }
        else if(lePhiUocTinh > 12000) {
        	return "12.000.000 VND";
        }
        else if(lePhiUocTinh > 11000) {
        	return "11.000.000 VND";
        }
        else if(lePhiUocTinh > 10000) {
        	return "10.000.000 VND";
        }
        else if(lePhiUocTinh > 9000) {
        	return "9.000.000 VND";
        }
        else if(lePhiUocTinh > 8000) {
        	return "8.000.000 VND";
        }
        else if(lePhiUocTinh > 7000) {
        	return "7.000.000 VND";
        }
        else if(lePhiUocTinh > 6000) {
        	return "6.000.000 VND";
        }
        else if(lePhiUocTinh > 5000) {
        	return "6.000.000 VND";
        }
        else if(lePhiUocTinh > 4000) {
        	return "4.000.000 VND";
        }
        else if(lePhiUocTinh > 3000) {
        	return "6.000.000 VND";
        }
        else if(lePhiUocTinh > 2000) {
        	return "2.000.000 VND";
        }

		return "1.000.000 VND";
	}
}
