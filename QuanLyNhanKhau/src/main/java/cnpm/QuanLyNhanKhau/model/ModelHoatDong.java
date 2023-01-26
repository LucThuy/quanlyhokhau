package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelHoatDong {
	
	private int idHoatDong;
	private int idNhanKhau;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String hoatDong;
	private String lePhi;
	private String ghiChu;
	private String xacNhan;
	
	private ModelNhanKhau modelNhanKhau;
	private ModelNhaVanHoa modelNhaVanHoa;

	
	public ModelHoatDong(int idHoatDong, int idNhanKhau, Date ngayBatDau, Date ngayKetThuc, String hoatDong,
		String lePhi, String ghiChu, String xacNhan) {
		super();
		this.idHoatDong  = idHoatDong;
		this.idNhanKhau  = idNhanKhau;
		this.ngayBatDau  = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.hoatDong    = hoatDong;
		this.lePhi       = lePhi;
		this.ghiChu      = ghiChu;
		this.xacNhan     = xacNhan;
	}

	public int getIdHoatDong() {
		return idHoatDong;
	}
	public void setIdHoatDong(int idHoatDong) {
		this.idHoatDong = idHoatDong;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getHoatDong() {
		return hoatDong;
	}
	public void setHoatDong(String hoatDong) {
		this.hoatDong = hoatDong;
	}
	public String getLePhi() {
		return lePhi;
	}
	public void setLePhi(String lePhi) {
		this.lePhi = lePhi;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getXacNhan() {
		return xacNhan;
	}
	public void setXacNhan(String xacNhan) {
		this.xacNhan = xacNhan;
	}
	
	public ModelNhanKhau getModelNhanKhau() {
		return modelNhanKhau;
	}
	public void setModelNhanKhau(ModelNhanKhau modelNhanKhau) {
		this.modelNhanKhau = modelNhanKhau;
	}
	
	public String getHoTenNguoiDangKy() {
		return modelNhanKhau.getHoTen();
	}
	
	
	
	
}
