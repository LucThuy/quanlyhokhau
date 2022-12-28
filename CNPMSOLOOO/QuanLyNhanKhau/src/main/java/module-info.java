module cnpm.QuanLyNhanKhau {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;

    opens cnpm.QuanLyNhanKhau to javafx.fxml;
    opens cnpm.QuanLyNhanKhau.controller to javafx.fxml;
    opens cnpm.QuanLyNhanKhau.model to javafx.fxml;
    exports cnpm.QuanLyNhanKhau;
    exports cnpm.QuanLyNhanKhau.controller;
    exports cnpm.QuanLyNhanKhau.model;
}
