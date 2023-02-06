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
import cnpm.QuanLyNhanKhau.model.ModelHoatDong;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class ControllerHoatDongQuanLy implements Initializable{

	@FXML
	private TableView<ModelHoatDong> tableviewHoatDong;
	@FXML
	private TableColumn<ModelHoatDong, String> tablecolumnHoTenNguoiDangKy;
	@FXML
	private TableColumn<ModelHoatDong, String> tablecolumnHoatDong;
	@FXML
	private TableColumn<ModelHoatDong, Date> tablecolumnNgayBatDau;
	@FXML
	private TableColumn<ModelHoatDong, Date> tablecolumnNgayKetThuc;
	@FXML
	private TableColumn<ModelHoatDong, String> tablecolumnLePhi;
	@FXML
	private TableColumn<ModelHoatDong, String> tablecolumnXacNhan;

	
	@FXML
	private Button buttonDangKy;
	@FXML
	private Button buttonLamMoi;
	@FXML
	private Button buttonChiTiet;
	
	private ModelHoatDong hoatDong;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		tablecolumnHoTenNguoiDangKy.setCellValueFactory(new PropertyValueFactory<>("hoTenNguoiDangKy"));
		tablecolumnHoatDong.setCellValueFactory(new PropertyValueFactory<>("hoatDong"));
		tablecolumnNgayBatDau.setCellValueFactory(new PropertyValueFactory<>("ngayBatDau"));
		tablecolumnNgayKetThuc.setCellValueFactory(new PropertyValueFactory<>("ngayKetThuc"));
		tablecolumnLePhi.setCellValueFactory(new PropertyValueFactory<>("lePhi"));
		tablecolumnXacNhan.setCellValueFactory(new PropertyValueFactory<>("xacNhan"));

		
		tableviewHoatDong.setRowFactory( val -> {
			TableRow<ModelHoatDong> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetailHoatDong();
				}
			});
			return row;
		});
		
		refreshTableViewHoatDong();
	}
	
	@FXML
	public void addHoatDong() {
		try {
			App.addStageForm("view/ViewFormAddHoatDong");
			ControllerFormAddHoatDong.setControllerHoatDongQuanLy(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void refreshHoatDong() {
		refreshTableViewHoatDong();
	}
	
	private void refreshTableViewHoatDong() {
		tableviewHoatDong.getItems().clear();
		
		List<ModelHoatDong> listHoatDong = Connector.getAllHoatDong();
		listHoatDong.forEach(hoatDong -> {
			tableviewHoatDong.getItems().add(hoatDong);
		});
	}
	
	@FXML
	public void showDetailHoatDong() {
		hoatDong = tableviewHoatDong.getSelectionModel().getSelectedItem();
		List<Integer> listId = new ArrayList<>();
		listId.add(hoatDong.getIdHoatDong());
		Holder.getInstance().setId(listId);	
		try {
			App.addStageForm("view/ViewFormDetailHoatDong");
			ControllerFormDetailHoatDong.setControllerHoatDongQuanLy(this);
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
}
