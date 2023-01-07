package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelTamTru;
import cnpm.QuanLyNhanKhau.model.ModelTamVang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class ControllerTamTruTamVang implements Initializable {
	
	
	@FXML
	private TableView<ModelTamTru> tableviewTamTru;
	@FXML
	private TableColumn<ModelTamTru, String> tablecolumnHoTenTamTru;
	@FXML
	private TableColumn<ModelTamTru, String> tablecolumnNoiTamTruTamTru;
	@FXML
	private TableColumn<ModelTamTru, Date> tablecolumnNgayHieuLucTamTru;
	@FXML
	private TableColumn<ModelTamTru, Date> tablecolumnNgayHetHieuLucTamTru;
	
	@FXML
	private TableView<ModelTamVang> tableviewTamVang;
	@FXML
	private TableColumn<ModelTamVang, String> tablecolumnHoTenTamVang;
	@FXML
	private TableColumn<ModelTamVang, String> tablecolumnNoiTamTruTamVang;
	@FXML
	private TableColumn<ModelTamVang, Date> tablecolumnNgayHieuLucTamVang;
	@FXML
	private TableColumn<ModelTamVang, Date> tablecolumnNgayHetHieuLucTamVang;
	
	@FXML
	private Button buttonDangKyTamTru;
	@FXML
	private Button buttonDangKyTamVang;
	@FXML
	private Button buttonChiTiet;
	
	private ModelTamTru nhanKhauTamTru;
	private ModelTamVang nhanKhauTamVang;
	private boolean showChiTiet;
	
	@Override
	public void initialize(URL location, ResourceBundle resoursces) {
		tablecolumnHoTenTamTru.setCellValueFactory(new PropertyValueFactory<>("hoTenNhanKhau"));
		tablecolumnNoiTamTruTamTru.setCellValueFactory(new PropertyValueFactory<>("noiTamTru"));
		tablecolumnNgayHieuLucTamTru.setCellValueFactory(new PropertyValueFactory<>("ngayHieuLuc"));
		tablecolumnNgayHetHieuLucTamTru.setCellValueFactory(new PropertyValueFactory<>("ngayHetHieuLuc"));

		tableviewTamTru.setRowFactory( val -> {
			TableRow<ModelTamTru> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
					nhanKhauTamTru = row.getItem();
					List<Integer> listId = new ArrayList<>();
					listId.add(nhanKhauTamTru.getIdTamTru());
					Holder.getInstance().setId(listId);	
					showChiTiet = true;
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetail();
				}
			});
			return row;
		});
		
		refreshTableViewTamTru();

		
		tablecolumnHoTenTamVang.setCellValueFactory(new PropertyValueFactory<>("hoTenNhanKhau"));
		tablecolumnNoiTamTruTamVang.setCellValueFactory(new PropertyValueFactory<>("noiTamTru"));
		tablecolumnNgayHieuLucTamVang.setCellValueFactory(new PropertyValueFactory<>("ngayHieuLuc"));
		tablecolumnNgayHetHieuLucTamVang.setCellValueFactory(new PropertyValueFactory<>("ngayHetHieuLuc"));

		tableviewTamVang.setRowFactory( val -> {
			TableRow<ModelTamVang> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
					nhanKhauTamVang = row.getItem();
					List<Integer> listId = new ArrayList<>();
					listId.add(nhanKhauTamVang.getIdTamVang());
					Holder.getInstance().setId(listId);	
					showChiTiet = false;
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetail();
				}
			});
			return row;
		});
		
		refreshTableViewTamVang();	
	}
	
	@FXML
	public void addTamTru() {
		try {
			App.addStageForm("view/viewFormAddTamTru");
			ControllerFormAddTamTru.setControllerTamTruTamVang(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void refreshTamTru() {
		refreshTableViewTamTru();
	}
	
	private void refreshTableViewTamTru() {
		tableviewTamTru.getItems().clear();
		
		List<ModelTamTru> listTamTru = Connector.getAllTamTru();
		listTamTru.forEach(TamTru -> {
			tableviewTamTru.getItems().add(TamTru);
		});
	}
	
	@FXML
	public void showDetailNhanKhauTamTru() {
		try {
			App.addStageForm("view/ViewFormDetailTamTru");
			ControllerFormDetailTamTru.setControllerTamTruTamVang(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	@FXML
	public void addTamVang() {
		try {
			App.addStageForm("view/viewFormAddTamVang");
			ControllerFormAddTamVang.setControllerTamTruTamVang(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void refreshTamVang() {
		refreshTableViewTamVang();
	}
	
	private void refreshTableViewTamVang() {
		tableviewTamVang.getItems().clear();
		
		List<ModelTamVang> listTamVang = Connector.getAllTamVang();
		listTamVang.forEach(TamVang -> {
			tableviewTamVang.getItems().add(TamVang);
		});
	}
	
	@FXML
	public void showDetailNhanKhauTamVang() {
		try {
			App.addStageForm("view/ViewFormDetailTamVang");
			ControllerFormDetailTamVang.setControllerTamTruTamVang(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	@FXML
	public void showDetail() {
		if(showChiTiet) {
			showDetailNhanKhauTamTru();
		} else {
			showDetailNhanKhauTamVang();
		}
	}
	
}
