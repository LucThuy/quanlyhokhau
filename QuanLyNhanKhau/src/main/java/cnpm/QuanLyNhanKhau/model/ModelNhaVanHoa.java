package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelNhaVanHoa {
	
	private int idKiemTra;
	private Date ngayKiemTra;
	private int soLuongBan;
	private int soLuongGhe;
	private int soLuongLoa;
	private int soLuongDai;
	private int soLuongManHinh;
	private int soLuongDen;
	private String hienTrangBan;
	private String hienTrangGhe;
	private String hienTrangLoa;
	private String hienTrangDai;
	private String hienTrangManHinh;
	private String hienTrangDen;
	
	public ModelNhaVanHoa(int idKiemTra, Date ngayKiemTra, int soLuongBan, String hienTrangBan,
			int soLuongGhe, String hienTrangGhe, int soLuongLoa, String hienTrangLoa, int soLuongDai,
			String hienTrangDai, int soLuongManHinh, String hienTrangManHinh, int soLuongDen,
			String hienTrangDen) {
		super();
		this.idKiemTra        = idKiemTra;
		this.ngayKiemTra      = ngayKiemTra;
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
	public Date getNgayKiemTra() {
		return ngayKiemTra;
	}
	public void setNgayKiemTra(Date ngayKiemTra) {
		this.ngayKiemTra = ngayKiemTra;
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
	
}
