package cnpm.QuanLyNhanKhau;

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
import cnpm.QuanLyNhanKhau.model.ModelUser;

public class Connector {

	private static String url = "jdbc:mysql://localhost:3306/quanlynhankhau";
	private static String user = "root";
	private static String password = "Minh_0112";

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

	public static boolean addNhanKhau(String hoTen, LocalDate ngaySinh, String gioiTinh, String cccd) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.nhankhau (`hoTen`, `ngaySinh`, `gioiTinh`, `cccd`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hoTen);
			ps.setDate(2, Date.valueOf(ngaySinh));
			ps.setString(3, gioiTinh);
			ps.setString(4, cccd);

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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelNhanKhau;
	}

	public static boolean editNhanKhau(ModelNhanKhau data) {
		boolean done = false;

		String query = "UPDATE quanlynhankhau.nhankhau SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, cccd = ?\n"
				+ "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, data.getHoTen());
			ps.setDate(2, data.getNgaySinh());
			ps.setString(3, data.getGioiTinh());
			ps.setString(4, data.getCccd());
			ps.setInt(5, data.getIdNhanKhau());

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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
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
						rs.getDate("ngaySinh"), rs.getString("gioiTinh"), rs.getString("cccd"));
				ModelHoKhauNhanKhau modelHoKhauNhanKhau = new ModelHoKhauNhanKhau(rs.getInt("idHoKhauNhanKhau"), rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("quanHe"));
				modelNhanKhau.setModelHoKhauNhanKhau(modelHoKhauNhanKhau);
				listNhanKhau.add(modelNhanKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNhanKhau;
	}

	public static boolean addHoKhau(int idNhanKhau, String diaChi) {
		boolean done = false;

		String query = "INSERT INTO quanlynhankhau.hokhau (`idNhanKhau`, `diaChi`) VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);
			ps.setString(2, diaChi);

			ps.executeUpdate();
			ModelHoKhau modelHoKhau = getHoKhau(idNhanKhau, diaChi);
			lsAddHoKhau(modelHoKhau.getIdHoKhau());
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
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"));
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
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"));
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

		String query = "UPDATE quanlynhankhau.hokhau SET idNhanKhau = ?, diaChi = ?\n"
				+ "WHERE idHoKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, data.getIdNhanKhau());
			ps.setString(2, data.getDiaChi());
			ps.setInt(3, data.getIdHoKhau());

			ps.executeUpdate();
			lsEditHoKhau(data.getIdHoKhau());
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
				ModelHoKhau modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
				listHoKhau.add(modelHoKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoKhau;
	}
	
	public static List<ModelHoKhau> searchHoKhauByIdNhanKhau(int idNhanKhau) {
		List<ModelHoKhau> listHoKhau = new ArrayList<>();

		String query = "SELECT * FROM quanlynhankhau.hokhau\n" + "WHERE idNhanKhau = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idNhanKhau);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ModelHoKhau modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"));
				ModelNhanKhau modelNhanKhau = queryNhanKhauByIdNhanKhau(rs.getInt("idNhanKhau"));
				modelHoKhau.setModelNhanKhau(modelNhanKhau);
				listHoKhau.add(modelHoKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHoKhau;
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
				modelHoKhau = new ModelHoKhau(rs.getInt("idHoKhau"), rs.getInt("idNhanKhau"), rs.getString("diaChi"));
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
}
