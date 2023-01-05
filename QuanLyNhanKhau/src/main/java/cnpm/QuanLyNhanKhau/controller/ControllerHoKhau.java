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
import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class ControllerHoKhau implements Initializable {

	@FXML
	private GridPane gridpane;

	@FXML
	private TableView<ModelHoKhau> tableviewHoKhau;
	@FXML
	private TableColumn<ModelHoKhau, String> tablecolumnHoTenChuHo;
	@FXML
	private TableColumn<ModelHoKhau, String> tablecolumnDiaChi;

	@FXML
	private TableView<ModelNhanKhau> tableviewHoKhauNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnHoTenHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, Date> tablecolumnNgaySinhHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnGioiTinhHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnCCCDHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnQuanHe;

	private ModelHoKhau hoKhau;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablecolumnHoTenChuHo.setCellValueFactory(new PropertyValueFactory<>("hoTenNhanKhau"));
		tablecolumnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		
		tableviewHoKhau.setRowFactory(val -> {
			TableRow<ModelHoKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					hoKhau = row.getItem();
					tableviewHoKhauNhanKhau.setVisible(true);
					gridpane.getRowConstraints().get(1).setPrefHeight(150);
					refreshHoKhauNhanKhau();
					hoKhau = row.getItem();
					List<Integer> listId = new ArrayList<Integer>();
					listId.add(hoKhau.getIdHoKhau());
					Holder.getInstance().setId(listId);
				}
				if (e.getButton().equals(MouseButton.PRIMARY) && row.isEmpty()) {
					tableviewHoKhauNhanKhau.setVisible(false);
					gridpane.getRowConstraints().get(1).setPrefHeight(0);
				}
				if (e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetailHoKhau();
				}
			});
			return row;
		});
		
		tablecolumnHoTenHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		tablecolumnNgaySinhHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		tablecolumnGioiTinhHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		tablecolumnCCCDHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("cccd"));
		tablecolumnQuanHe.setCellValueFactory(new PropertyValueFactory<>("quanHe"));

		refreshTableViewHoKhau();
	}

	@FXML
	public void addHoKhau() {
		try {
			App.addStageForm("view/ViewFormAddHoKhau");
			ControllerFormAddHoKhau.setControllerHoKhau(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void refreshHoKhau() {
		tableviewHoKhauNhanKhau.setVisible(false);
		gridpane.getRowConstraints().get(1).setPrefHeight(0);
		
		refreshTableViewHoKhau();
	}
	
	public void refreshHoKhauNhanKhau() {
		tableviewHoKhauNhanKhau.setVisible(true);
		gridpane.getRowConstraints().get(1).setPrefHeight(150);
		
		refreshTableViewHoKhauNhanKhau();
	}

	private void refreshTableViewHoKhau() {
		tableviewHoKhau.getItems().clear();

		List<ModelHoKhau> listHoKhau = Connector.getAllHoKhau();
		listHoKhau.forEach(hoKhau -> {
			tableviewHoKhau.getItems().add(hoKhau);
		});
	}

	private void refreshTableViewHoKhauNhanKhau() {
		tableviewHoKhauNhanKhau.getItems().clear();

		List<ModelNhanKhau> listNhanKhau = Connector.searchNhanKhauByIdHoKhau(hoKhau.getIdHoKhau());
		listNhanKhau.forEach(nhanKhau -> {
			tableviewHoKhauNhanKhau.getItems().add(nhanKhau);
		});
	}
	
	@FXML
	private void showDetailHoKhau() {
		try {
			App.addStageForm("view/ViewFormDetailHoKhau");
			ControllerFormDetailHoKhau.setControllerHoKhau(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
