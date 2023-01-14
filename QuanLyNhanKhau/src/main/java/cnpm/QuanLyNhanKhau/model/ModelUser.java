package cnpm.QuanLyNhanKhau.model;

public class ModelUser {
	private int idUser;
	private String tenNguoiDung;
	private String taiKhoan;
	private String matKhau;
	
	private String role;
	private String capQuyen;
	
	public ModelUser(int idUser, String tenNguoiDung, String taiKhoan, String matKhau, String role,
			String capQuyen) {
		super();
		this.idUser       = idUser;
		this.tenNguoiDung = tenNguoiDung;
		this.taiKhoan     = taiKhoan;
		this.matKhau      = matKhau;
		this.role         = role;
		this.capQuyen     = capQuyen;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getTenNguoiDung() {
		return tenNguoiDung;
	}
	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCapQuyen() {
		return capQuyen;
	}
	public void setCapQuyen(String capQuyen) {
		this.capQuyen = capQuyen;
	}
}
