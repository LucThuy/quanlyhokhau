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
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class ControllerNhanKhau implements Initializable{

	@FXML
	private TableView<ModelNhanKhau> tableviewNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnHoTen;
	@FXML
	private TableColumn<ModelNhanKhau, Date> tablecolumnNgaySinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnGioiTinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnCCCD;
	
	@FXML
	private Button buttonThemMoi;
	@FXML
	private Button buttonLamMoi;
	@FXML
	private Button buttonDangKyTamTru;
	@FXML
	private Button buttonDangKyTamVang;
	
	private ModelNhanKhau nhanKhau;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		tablecolumnHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		tablecolumnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		tablecolumnGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		tablecolumnCCCD.setCellValueFactory(new PropertyValueFactory<>("cccd"));
		
		tableviewNhanKhau.setRowFactory( val -> {
			TableRow<ModelNhanKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					nhanKhau = row.getItem();
					List<Integer> listId = new ArrayList<>();
					listId.add(nhanKhau.getIdNhanKhau());
					Holder.getInstance().setId(listId);
					try {
						App.addStageForm("view/ViewFormDetailNhanKhau");
						ControllerFormDetailNhanKhau.setControllerNhanKhau(this);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			return row;
		});
		
		refreshTableViewNhanKhau();
	}
	
	@FXML
	public void addNhanKhau() {
		try {
			App.addStageForm("view/ViewFormAddNhanKhau");
			ControllerFormAddNhanKhau.setControllerNhanKhau(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void refreshNhanKhau() {
		refreshTableViewNhanKhau();
	}
	
	private void refreshTableViewNhanKhau() {
		tableviewNhanKhau.getItems().clear();
		
		List<ModelNhanKhau> listNhanKhau = Connector.getAllNhanKhau();
		listNhanKhau.forEach(nhanKhau -> {
			tableviewNhanKhau.getItems().add(nhanKhau);
		});
	}
	
	@FXML
	public void dangKyTamVang() {
		try {
			App.addStageForm("view/ViewFormDangKyTamVang");
			ControllerFormDangKyTamVang.setControllerNhanKhau(this);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
