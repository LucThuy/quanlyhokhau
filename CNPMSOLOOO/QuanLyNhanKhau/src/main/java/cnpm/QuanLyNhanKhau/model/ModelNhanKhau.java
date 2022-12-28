package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelNhanKhau {
	private int idNhanKhau;
	private String hoTen;
	private Date ngaySinh;
	private String gioiTinh;
	private String cccd;
	
	private ModelHoKhauNhanKhau modelHoKhauNhanKhau;
	
	public ModelNhanKhau(int idNhanKhau, String hoTen, Date ngaySinh, String gioiTinh, String cccd) {
		super();
		this.idNhanKhau = idNhanKhau;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.cccd = cccd;
	}
	
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh.toLowerCase();
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}	

	public ModelHoKhauNhanKhau getModelHoKhauNhanKhau() {
		return modelHoKhauNhanKhau;
	}

	public void setModelHoKhauNhanKhau(ModelHoKhauNhanKhau modelHoKhauNhanKhau) {
		this.modelHoKhauNhanKhau = modelHoKhauNhanKhau;
	}
	
	public String getQuanHe() {
		return modelHoKhauNhanKhau.getQuanHe();
	}
}
