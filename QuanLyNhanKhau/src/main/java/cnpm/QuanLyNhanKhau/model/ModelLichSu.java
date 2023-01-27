package cnpm.QuanLyNhanKhau.model;

import java.sql.Timestamp;

public class ModelLichSu {
	private int idLichSu;
	private String thaoTac;
	private int idUser;
	private Timestamp thoiGian;
	private int idNhanKhau;
	private int idHoKhau;
	private int idHoKhauNhanKhau;
	private int idTamTru;
	private int idTamVang;
	private int idKiemTra;
	private int idHoatDong;
	
	private ModelUser modelUser;
	private ModelNhanKhau modelNhanKhau;
	
	public ModelLichSu(int idLichSu, String thaoTac, int idUser, Timestamp thoiGian) {
		super();
		this.idLichSu = idLichSu;
		this.thaoTac = thaoTac;
		this.idUser = idUser;
		this.thoiGian = thoiGian;
	}

	public int getIdLichSu() {
		return idLichSu;
	}
	public void setIdLichSu(int idLichSu) {
		this.idLichSu = idLichSu;
	}
	public String getThaoTac() {
		return thaoTac;
	}
	public void setThaoTac(String thaoTac) {
		this.thaoTac = thaoTac;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Timestamp getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public int getIdHoKhau() {
		return idHoKhau;
	}

	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public int getIdHoKhauNhanKhau() {
		return idHoKhauNhanKhau;
	}
	public void setIdHoKhauNhanKhau(int idHoKhauNhanKhau) {
		this.idHoKhauNhanKhau = idHoKhauNhanKhau;
	}
	public int getIdTamTru() {
		return idTamTru;
	}
	public void setIdTamTru(int idTamTru) {
		this.idTamTru = idTamTru;
	}
	public int getIdTamVang() {
		return idTamVang;
	}
	public void setIdTamVang(int idTamVang) {
		this.idTamVang = idTamVang;
	}
	public int getIdKiemTra() {
		return idKiemTra;
	}
	public void setIdKiemTra(int idKiemTra) {
		this.idKiemTra = idKiemTra;
	}
	public int getIdHoatDong() {
		return idHoatDong;
	}
	public void setIdHoatDong(int idHoatDong) {
		this.idHoatDong = idHoatDong;
	}
	
	public ModelNhanKhau getModelNhanKhau() {
		return modelNhanKhau;
	}
	public void setModelNhanKhau(ModelNhanKhau modelNhanKhau) {
		this.modelNhanKhau = modelNhanKhau;
	}
	
	public String getHoTenThayDoi() {
		return modelNhanKhau.getHoTen();
	}
}
