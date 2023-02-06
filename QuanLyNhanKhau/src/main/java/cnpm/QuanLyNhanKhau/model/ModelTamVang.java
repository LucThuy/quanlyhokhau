package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelTamVang {
	private int idTamVang;
	private int idNhanKhau;
	private String noiTamTru;
	private Date ngayHieuLuc;
	private Date ngayHetHieuLuc;
	private String lyDo;
	
	private ModelNhanKhau modelNhanKhau;
	
	@SuppressWarnings("exports")
	public ModelTamVang(int idTamVang, int idNhanKhau, String noiTamTru,
						Date ngayHieuLuc, Date ngayHetHieuLuc, String lyDo) {
		super();
		this.idTamVang      = idTamVang;
		this.idNhanKhau     = idNhanKhau;
		this.noiTamTru      = noiTamTru;
		this.ngayHieuLuc    = ngayHieuLuc;
		this.ngayHetHieuLuc = ngayHetHieuLuc;
		this.lyDo           = lyDo;
	}

	public int getIdTamVang() {
		return idTamVang;
	}
	public void setIdTamVang(int idTamVang) {
		this.idTamVang = idTamVang;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getNoiTamTru() {
		return noiTamTru;
	}
	public void setNoiTamTru(String noiTamTru) {
		this.noiTamTru = noiTamTru;
	}
	@SuppressWarnings("exports")
	public Date getNgayHieuLuc() {
		return ngayHieuLuc;
	}
	@SuppressWarnings("exports")
	public void setNgayHieuLuc(Date ngayHieuLuc) {
		this.ngayHieuLuc = ngayHieuLuc;
	}
	@SuppressWarnings("exports")
	public Date getNgayHetHieuLuc() {
		return ngayHetHieuLuc;
	}
	@SuppressWarnings("exports")
	public void setNgayHetHieuLuc(Date ngayHetHieuLuc) {
		this.ngayHetHieuLuc = ngayHetHieuLuc;
	}
	public String getLyDo() {
		return lyDo;
	}
	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
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
