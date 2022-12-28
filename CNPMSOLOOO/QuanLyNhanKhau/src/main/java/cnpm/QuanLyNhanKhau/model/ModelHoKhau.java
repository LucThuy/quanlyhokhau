package cnpm.QuanLyNhanKhau.model;

import cnpm.QuanLyNhanKhau.Connector;

public class ModelHoKhau {
	private int idHoKhau;
	private int idNhanKhau;
	private String diaChi;
	
	private ModelNhanKhau modelNhanKhau;
	
	public ModelHoKhau(int idHoKhau, int idNhanKhau, String diaChi) {
		super();
		this.idHoKhau = idHoKhau;
		this.idNhanKhau = idNhanKhau;
		this.diaChi = diaChi;
	}
	
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public ModelNhanKhau getModelNhanKhau() {
		return modelNhanKhau;
	}
	public void setModelNhanKhau(ModelNhanKhau modelNhanKhau) {
		this.modelNhanKhau = modelNhanKhau;
	}
	
	public String getHoTenNhanKhau() {
		return modelNhanKhau.getHoTen();
	}
}
