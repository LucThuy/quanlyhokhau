package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelHoatDong {
	
	private int idHoatDong;
	private int idNhanKhau;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String hoatDong;
	private int soLuongBan;
	private int soLuongGhe;
	private int soLuongLoa;
	private int soLuongDai;
	private int soLuongManHinh;
	private int soLuongDen;
	private String lePhi;
	private String ghiChu;
	private String xacNhan;
	
	private ModelNhanKhau modelNhanKhau;
//	private ModelNhaVanHoa modelNhaVanHoa;

	
	@SuppressWarnings("exports")
	public ModelHoatDong(int idHoatDong, int idNhanKhau, Date ngayBatDau, Date ngayKetThuc,
			String hoatDong, int soLuongBan, int soLuongGhe, int soLuongLoa, int soLuongDai,
			int soLuongManHinh, int soLuongDen, String lePhi, String ghiChu, String xacNhan) {
		super();
		this.idHoatDong     = idHoatDong;
		this.idNhanKhau     = idNhanKhau;
		this.ngayBatDau     = ngayBatDau;
		this.ngayKetThuc    = ngayKetThuc;
		this.hoatDong       = hoatDong;
		this.lePhi          = lePhi;
		this.soLuongBan     = soLuongBan;
		this.soLuongGhe     = soLuongGhe;
		this.soLuongLoa     = soLuongLoa;
		this.soLuongDai     = soLuongDai;
		this.soLuongManHinh = soLuongManHinh;
		this.soLuongDen     = soLuongDen;
		this.ghiChu         = ghiChu;
		this.xacNhan        = xacNhan;
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
	@SuppressWarnings("exports")
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	@SuppressWarnings("exports")
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	@SuppressWarnings("exports")
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	@SuppressWarnings("exports")
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getHoatDong() {
		return hoatDong;
	}
	public void setHoatDong(String hoatDong) {
		this.hoatDong = hoatDong;
	}
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public int getSoLuongGhe() {
		return soLuongGhe;
	}
	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}
	public int getSoLuongLoa() {
		return soLuongLoa;
	}
	public void setSoLuongLoa(int soLuongLoa) {
		this.soLuongLoa = soLuongLoa;
	}
	public int getSoLuongDai() {
		return soLuongDai;
	}
	public void setSoLuongDai(int soLuongDai) {
		this.soLuongDai = soLuongDai;
	}
	public int getSoLuongManHinh() {
		return soLuongManHinh;
	}
	public void setSoLuongManHinh(int soLuongManHinh) {
		this.soLuongManHinh = soLuongManHinh;
	}
	public int getSoLuongDen() {
		return soLuongDen;
	}
	public void setSoLuongDen(int soLuongDen) {
		this.soLuongDen = soLuongDen;
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
