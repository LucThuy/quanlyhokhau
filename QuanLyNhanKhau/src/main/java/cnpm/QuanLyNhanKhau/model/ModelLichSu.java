package cnpm.QuanLyNhanKhau.model;

import java.sql.Timestamp;

public class ModelLichSu {
	private int idLichSu;
	private String thaoTac;
	private int idUser;
	private Timestamp thoiGian;
	private int idNhanKhau;
	
	private ModelUser modelUser;
	
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
	
}
