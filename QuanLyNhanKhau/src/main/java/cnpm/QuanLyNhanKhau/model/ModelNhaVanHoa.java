package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelNhaVanHoa {
	
	private int idKiemTra;
	private int idUser;
	private Date ngayThucHien;
	private String soLuongBan;
	private String soLuongGhe;
	private String soLuongLoa;
	private String soLuongDai;
	private String soLuongManHinh;
	private String soLuongDen;
	private String hienTrangBan;
	private String hienTrangGhe;
	private String hienTrangLoa;
	private String hienTrangDai;
	private String hienTrangManHinh;
	private String hienTrangDen;
	
	private ModelUser modelUser;
	
	public ModelNhaVanHoa(int idKiemTra, int idUser, Date ngayThucHien, String soLuongBan, String hienTrangBan,
			String soLuongGhe, String hienTrangGhe, String soLuongLoa, String hienTrangLoa, String soLuongDai,
			String hienTrangDai, String soLuongManHinh, String hienTrangManHinh, String soLuongDen,
			String hienTrangDen) {
		super();
		this.idKiemTra        = idKiemTra;
		this.idUser           = idUser;
		this.ngayThucHien     = ngayThucHien;
		this.soLuongBan       = soLuongBan;
		this.hienTrangBan     = hienTrangBan;
		this.soLuongGhe       = soLuongGhe;
		this.hienTrangGhe     = hienTrangGhe;
		this.soLuongLoa       = soLuongLoa;
		this.hienTrangLoa     = hienTrangLoa;
		this.soLuongDai       = soLuongDai;
		this.hienTrangDai     = hienTrangDai;
		this.soLuongManHinh   = soLuongManHinh;
		this.hienTrangManHinh = hienTrangManHinh;
		this.soLuongDen       = soLuongDen;
		this.hienTrangDen     = hienTrangDen;
	}

	public int getIdKiemTra() {
		return idKiemTra;
	}
	public void setIdKiemTra(int idKiemTra) {
		this.idKiemTra = idKiemTra;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Date getNgayThucHien() {
		return ngayThucHien;
	}
	public void setNgayThucHien(Date ngayThucHien) {
		this.ngayThucHien = ngayThucHien;
	}
	public String getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(String soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public String getSoLuongGhe() {
		return soLuongGhe;
	}
	public void setSoLuongGhe(String soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}
	public String getSoLuongLoa() {
		return soLuongLoa;
	}
	public void setSoLuongLoa(String soLuongLoa) {
		this.soLuongLoa = soLuongLoa;
	}
	public String getSoLuongDai() {
		return soLuongDai;
	}
	public void setSoLuongDai(String soLuongDai) {
		this.soLuongDai = soLuongDai;
	}
	public String getSoLuongManHinh() {
		return soLuongManHinh;
	}
	public void setSoLuongManHinh(String soLuongManHinh) {
		this.soLuongManHinh = soLuongManHinh;
	}
	public String getSoLuongDen() {
		return soLuongDen;
	}
	public void setSoLuongDen(String soLuongDen) {
		this.soLuongDen = soLuongDen;
	}
	public String getHienTrangBan() {
		return hienTrangBan;
	}
	public void setHienTrangBan(String hienTrangBan) {
		this.hienTrangBan = hienTrangBan;
	}
	public String getHienTrangGhe() {
		return hienTrangGhe;
	}
	public void setHienTrangGhe(String hienTrangGhe) {
		this.hienTrangGhe = hienTrangGhe;
	}
	public String getHienTrangLoa() {
		return hienTrangLoa;
	}
	public void setHienTrangLoa(String hienTrangLoa) {
		this.hienTrangLoa = hienTrangLoa;
	}
	public String getHienTrangDai() {
		return hienTrangDai;
	}
	public void setHienTrangDai(String hienTrangDai) {
		this.hienTrangDai = hienTrangDai;
	}
	public String getHienTrangManHinh() {
		return hienTrangManHinh;
	}
	public void setHienTrangManHinh(String hienTrangManHinh) {
		this.hienTrangManHinh = hienTrangManHinh;
	}
	public String getHienTrangDen() {
		return hienTrangDen;
	}
	public void setHienTrangDen(String hienTrangDen) {
		this.hienTrangDen = hienTrangDen;
	}
	
	public ModelUser getModelUser() {
		return modelUser;
	}

	public void setModelUser(ModelUser modelUser) {
		this.modelUser = modelUser;
	}
	
	public String getTenNguoiDung() {
		return modelUser.getTenNguoiDung();
	}
	
}
