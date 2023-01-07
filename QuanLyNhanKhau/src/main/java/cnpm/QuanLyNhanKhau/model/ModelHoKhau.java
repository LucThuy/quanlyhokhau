package cnpm.QuanLyNhanKhau.model;

import cnpm.QuanLyNhanKhau.Connector;

public class ModelHoKhau {
	private int idHoKhau;
	private int idNhanKhau;
	private String diaChi;
	private String soNha;
	private String duongPho;
	private String phuong;
	private String quan;
	
	private ModelNhanKhau modelNhanKhau;
	
	public ModelHoKhau(int idHoKhau, int idNhanKhau, String diaChi, String soNha, String duongPho, String phuong,
					String quan) {
		super();
		this.idHoKhau   = idHoKhau;
		this.idNhanKhau = idNhanKhau;
		this.diaChi     = diaChi;
		this.soNha      = soNha;
		this.duongPho   = duongPho;
		this.phuong     = phuong;
		this.quan       = quan;
	}
	
	public ModelHoKhau() {
		
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
	public String getSoNha() {
		return soNha;
	}
	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}
	public String getDuongPho() {
		return duongPho;
	}
	public void setDuongPho(String duongPho) {
		this.duongPho = duongPho;
	}
	public String getPhuong() {
		return phuong;
	}
	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}
	public String getQuan() {
		return quan;
	}
	public void setQuan(String quan) {
		this.quan = quan;
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
