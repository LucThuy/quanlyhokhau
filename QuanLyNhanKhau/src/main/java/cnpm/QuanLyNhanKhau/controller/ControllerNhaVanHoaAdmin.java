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
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class ControllerNhaVanHoaAdmin implements Initializable{

	@FXML
	private TableView<ModelNhaVanHoa> tableviewQuanLy;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnNguoiThucHien;
	@FXML
	private TableColumn<ModelNhaVanHoa, Date> tablecolumnNgayThucHien;

	
	@FXML
	private Button buttonThemMoi;
	@FXML
	private Button buttonLamMoi;
	@FXML
	private Button buttonChiTiet;
	
	private ModelNhaVanHoa quanLy;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablecolumnNguoiThucHien.setCellValueFactory(new PropertyValueFactory<>("tenNguoiDung"));
		tablecolumnNgayThucHien.setCellValueFactory(new PropertyValueFactory<>("ngayThucHien"));

		
		tableviewQuanLy.setRowFactory( val -> {
			TableRow<ModelNhaVanHoa> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetailQuanLy();
				}
			});
			return row;
		});
		
		refreshTableViewQuanLy();
	}
	
	@FXML
	public void addQuanLy() {
		try {
			App.addStageForm("view/ViewFormAddQuanLyNhaVanHoa");
			ControllerFormAddQuanLyNhaVanHoa.setControllerNhaVanHoaAdmin(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void refreshQuanLyNhaVanHoa() {
		refreshTableViewQuanLy();
	}
	
	private void refreshTableViewQuanLy() {
		tableviewQuanLy.getItems().clear();
		
		List<ModelNhaVanHoa> listQuanLy = Connector.getAllQuanLyNhaVanHoa();
		listQuanLy.forEach(quanLy -> {
			tableviewQuanLy.getItems().add(quanLy);
		});
	}
	
	@FXML
	public void showDetailQuanLy() {
		quanLy = tableviewQuanLy.getSelectionModel().getSelectedItem();
		List<Integer> listId = new ArrayList<>();
		listId.add(quanLy.getIdKiemTra());
		Holder.getInstance().setId(listId);	
		try {
			App.addStageForm("view/ViewFormDetailQuanLyNhaVanHoa");
			ControllerFormDetailQuanLyNhaVanHoa.setControllerNhaVanHoaAdmin(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	@FXML
	public void editLePhi() {
		try {
			App.addStageForm("view/ViewFormDetailLePhi");
			ControllerFormDetailLePhi.setControllerNhaVanHoaAdmin(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
